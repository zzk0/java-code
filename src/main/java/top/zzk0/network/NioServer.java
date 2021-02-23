package top.zzk0.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class NioServer {

    private static final Charset charset = StandardCharsets.UTF_8;
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        // 用来复用线程
        Selector selector = Selector.open();

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverChannel.bind(new InetSocketAddress(8888));

        while (selector.select() > 0) {
            for (SelectionKey key : selector.selectedKeys()) {
                selector.selectedKeys().remove(key);

                // 接受请求
                if (key.isAcceptable()) {
                    SocketChannel socket = serverChannel.accept();
                    System.out.println("新来了一个连接请求...");
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ, count++);
                    key.interestOps(SelectionKey.OP_ACCEPT);
                }

                // 接受输入
                if (key.isReadable()) {
                    try {
                        SocketChannel socket = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder content = new StringBuilder();
                        while (socket.read(buffer) > 0) {
                            buffer.flip();
                            content.append(charset.decode(buffer));
                        }
                        System.out.printf("客户端 %d 发来的内容: %s\n", (int) key.attachment(), content);

                        // 往回写
                        byte[] byteContent = content.toString().getBytes();
                        ByteBuffer backBuffer = ByteBuffer.wrap(byteContent);
                        socket.write(backBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    catch (Exception e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            }
        }
    }
}

/*
使用这种方式和 BIO 无异, 线程没有复用
*/

class NioSocketProcessor extends Thread {
    private static int count = 0;
    private static Charset charset = StandardCharsets.UTF_8;
    int id;
    SocketChannel socket;

    public NioSocketProcessor(SocketChannel socket) {
        this.socket = socket;
        id = count;
        count++;
    }

    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            CharsetDecoder decoder = charset.newDecoder();
            while (socket.read(buffer) != 0) {
                buffer.flip();
                String content = decoder.decode(buffer).toString();
                System.out.printf("客户端 %d 发来的内容: %s\n", id, content);
                buffer.flip();
                socket.write(buffer);
                if ("EXIT".equals(content)) {
                    socket.close();
                    break;
                }
                buffer.clear();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}