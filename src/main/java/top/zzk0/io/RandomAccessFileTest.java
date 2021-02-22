package top.zzk0.io;

/*
不像输入输出流, 有字节流和字符流的区分, RandomAccessFile 操作的缓冲区都是 byte 类型的
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;

public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        URL url = ClassLoader.getSystemResource("1.txt");
        RandomAccessFile file = new RandomAccessFile(url.getPath(), "rw");
        file.seek(file.length());
        String content = "追加的内容!";
        file.write(content.getBytes());
        file.close();
    }
}
