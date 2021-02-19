package top.zzk0.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SystemIn {

    public static void main(String[] args) {
        read();
        System.out.println("------------------------");
        read1();
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);
        // scanner.useDelimiter(" ");
        // hasNextLong 如果输入的不是整数, 将会退出
        // hasNext 方法会阻塞, 直到有东西输入
        // 当我们按下回车的时候才将东西输入进来, 比如 先输入 aaa 回车 再输入 bbb
        // 在 scanner 看来是 aaabbb 而不是 aaa\nbbb
        // 因此当用空格做分隔符的时候 a b c < 会输出三个字符 a b c< 会输出两个, 因为 c 后面没有分隔符
        while (scanner.hasNext()) {
            String msg = scanner.next();
            if ("exit".equals(msg)) return;
            System.out.println(msg);
        }
    }

    private static void read1() {
        // Reader 是字符流, System.in 是字节流, BufferedReader 是包装流
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String msg = br.readLine();
            while (msg != null) {
                if ("exit".equals(msg)) return;
                System.out.println(msg);
                msg = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
