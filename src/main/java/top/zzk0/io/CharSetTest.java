package top.zzk0.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.SortedMap;

public class CharSetTest {

    public static void main(String[] args) throws Exception {
        // 获取所有的字符集
        Map<String, Charset> map = Charset.availableCharsets();
        System.out.println(map.size());

        // 尝试编码解码
        Charset gbk = Charset.forName("GBK");
        CharsetEncoder encoder = gbk.newEncoder();
        CharsetDecoder decoder = gbk.newDecoder();
        CharBuffer buffer = CharBuffer.allocate(8);
        buffer.put("东");
        buffer.put("南");
        buffer.put("西");
        buffer.put("北");
        buffer.put("中");
        buffer.flip();

        // 编码: 将数据变成字节
        ByteBuffer byteBuffer = encoder.encode(buffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get(i) + " ");
        }

        // 解码: 将字节变成字符
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get(i) + " ");
        }
    }
}
