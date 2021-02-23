package top.zzk0.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AioServer {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(pool);
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(8888));
        server.accept(null, new ConnectionCompletionHandler(server));
        System.in.read();
    }
}

class ConnectionCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

    AsynchronousServerSocketChannel server;

    public ConnectionCompletionHandler(AsynchronousServerSocketChannel server) {
        this.server = server;
    }

    @Override
    public void completed(AsynchronousSocketChannel socket, Object attachment) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socket.read(buffer, buffer, new ReadCompletionHandler(socket)); // 非阻塞, 执行到这里就结束了
        System.out.println("新来了一个连接请求...");
        server.accept(null, this);
    }

    @Override
    public void failed(Throwable exc, Object attachment) {

    }
}

class ReadCompletionHandler implements CompletionHandler<Integer, Object> {

    private static int count = 0;
    private int id;
    AsynchronousSocketChannel socket;

    public ReadCompletionHandler(AsynchronousSocketChannel socket) {
        this.socket = socket;
        id = count;
        count++;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        // 如果为 0, 那么可以假设客户端断开连接
        if (result == -1) {
            return;
        }
        // 当进来的时候, buffer 已经读好了, 0 ~ pos 是读取的内容
        ByteBuffer buffer = (ByteBuffer) attachment;
        buffer.flip();

        // 调用之后, pos = limit, 后面需要重新 flip 一下
        String content = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.printf("客户端 %d 发来的内容: %s\n", id, content);
        byte[] byteContent = content.getBytes();
        socket.write(ByteBuffer.wrap(byteContent));

        // 需要准备进行下一次读取才行, 否则到这里就结束了, 不再监听客户端了
        buffer.clear();
        socket.read(buffer, buffer, this);
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println(exc);
    }
}
