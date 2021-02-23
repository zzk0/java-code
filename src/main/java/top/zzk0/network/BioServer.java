package top.zzk0.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            System.out.println("新来了一个连接请求...");
            new BioSocketProcessor(socket).start();
        }
    }

}

class BioSocketProcessor extends Thread {
    private static int count = 0;
    int id;
    Socket socket;

    public BioSocketProcessor(Socket socket) {
        this.socket = socket;
        id = count;
        count++;
    }

    @Override
    public void run() {
        try {
            InputStream socketIn = socket.getInputStream();
            OutputStream socketOut = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int hasRead = 0;

            while ((hasRead = socketIn.read(buffer)) > 0) {
                String content = new String(buffer, 0, hasRead);
                System.out.printf("客户端 %d 发来的内容: %s\n", id, content);
                socketOut.write(buffer, 0, hasRead);
                if ("EXIT".equals(content)) {
                    socket.close();
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
