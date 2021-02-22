package top.zzk0.io;

/*
输入输出流
抽象基类有四个: InputStream, OutputStream, Reader, Writer
前两个是字节流, 后两个是字符流, 前者单位是字节 8bit, 后者单位是字符 16bit

大部分类需要借助缓冲区来进行读写, 部分类(BufferedReader, PrintStream)就不用

这些输入流还提供了方法来操作指针, skip, mark 之类的, 输出流没有...
*/

import java.io.*;
import java.net.URL;

public class StreamTest {

    public static void main(String[] args) throws IOException {
        // 例子1: 使用 InputStream
        URL url = ClassLoader.getSystemResource("dir/2.txt");
        InputStream is = url.openStream();
        byte[] buffer = new byte[4]; // 可能会读取半个中文字符, 出现乱码
        int hasRead = 0;
        while ((hasRead = is.read(buffer)) > 0) {
            System.out.print(new String(buffer, 0, hasRead));
        }
        is.close();

        // 例子2: 使用 Reader, 避免乱码
        is = url.openStream();
        Reader reader = new InputStreamReader(is);
        char[] buffer1 = new char[1];
        while ((hasRead = reader.read(buffer1)) > 0) {
            System.out.print(new String(buffer1, 0, hasRead));
        }
        reader.close();

        // 例子3: 使用 FileInputStream
        File file = new File(url.getPath());
        FileInputStream fis = new FileInputStream(file);
        while ((hasRead = fis.read(buffer)) > 0) {
            System.out.print(new String(buffer, 0, hasRead));
        }
        fis.close();

        // 例子4: 复制文件
        URL url1 = ClassLoader.getSystemResource("dir");
        File file1 = File.createTempFile("xxx", ".txt", new File(url1.getPath()));
        if (!file1.exists()) {
            boolean success = file1.createNewFile();
            if (!success) throw  new IOException("文件创建失败");
        }
        try(FileInputStream fis1 = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file1)) {
            byte[] buffer2 = new byte[1];
            while ((hasRead = fis1.read(buffer2)) > 0) {
                fos.write(buffer2);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // 例子5: BufferedReader, 相比普通的 Reader, 就是多了个 readLine 方法, 而且无法设置分割符, Scanner 真香!
        BufferedReader bis = new BufferedReader(new FileReader(file1));
        String str = null;
        while ((str = bis.readLine()) != null) {
            System.out.println(str);
        }

        // 例子6: PrintWriter
        PrintWriter pw = new PrintWriter(System.out);
        pw.printf("%d, %f\n", 1, 0.5);
        pw.flush(); // 必须要手动刷新一下

        // 例子7: StringWriter
        StringWriter sw = new StringWriter();
        PrintWriter spw = new PrintWriter(sw);
        spw.printf("pi = %.4f, e = %.4f", Math.PI, Math.E);
        String ansStr = sw.toString();
        System.out.println(ansStr);

        // 例子8: Append, 实际上并不能 append, 要 append 还得看 RandomAccessFile
        FileWriter fileWriter = new FileWriter(file1);
        fileWriter.append("asdf");
        fileWriter.append("zxcvzxcv");
        fileWriter.close();
    }
}
