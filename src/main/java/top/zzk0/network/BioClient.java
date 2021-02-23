package top.zzk0.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8888);

        // 监听服务器
        InputStream socketIn = socket.getInputStream();
        new Thread(new ReceiveCallback(socketIn)).start();

        // 往服务器写
        PrintStream socketOut = new PrintStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String content = scanner.nextLine();
            socketOut.print(content);
            if ("EXIT".equals(content)) break;
        }
    }
}

class ReceiveCallback implements Runnable {
    InputStream inputStream;

    public ReceiveCallback(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        try {
            while ((hasRead = inputStream.read(buffer)) > 0) {
                String content = new String(buffer, 0, hasRead);
                System.out.println(content);
                if ("EXIT".equals(content)) {
                    inputStream.close();
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
