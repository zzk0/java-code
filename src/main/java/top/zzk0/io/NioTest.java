package top.zzk0.io;

/*
NIO 和原来的 IO 对比来看, 只不过是将一些东西封装起来而已, 使用起来差不多
比如, 每次读取文件之前, 都需要创建一个 byte[], NIO 中直接封装一个 Buffer 类(提供 pos, limit, capacity 几个指针)
Buffer 需要用 XxxBuffer 的 allocate 静态方法获取
Channel 对应到原来的流, 获取方法是调用流(XXXXStream)的 getChannel 方法, RandomAccessFile 中也有这个方法

Buffer 有两种 get 方式, 一个是无参的, 每次取完之后 position 会自增, 另一种按位子取, position 保持不变

感受一下极其不人性的 IO 设计... 我就想打印文件内容罢了
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("1.txt");
        File file = new File(url.getPath());
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            // MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            ByteBuffer buffer = ByteBuffer.allocate(8);
            while (channel.read(buffer) != -1) {
                buffer.flip(); // 将 limit 移动到已经读取的地方
                byte[] buf  = new byte[buffer.limit()];
                buffer.get(buf);
                System.out.print(new String(buf));
                buffer.clear(); // 重置 position, limit
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
