package top.zzk0.util;

/*
这个文件介绍 System 类和 Runtime 类

System 表示 Java 程序运行的平台, 标准输入输出, 错误输出, 环境变量, 系统属性, 时间属性
Runtime 表示运行时环境, 处理器, 内存等信息, 还可以运行命令

两个类都有 gc(), runFinalization() 方法来通知垃圾回收
*/

import java.util.Map;
import java.util.Properties;

public class SystemRuntime {

    public static void main(String[] args) {
        systemTest();
        System.out.println("---------------------");
        runtimeTest();
    }

    private static void systemTest() {
        // 环境变量
        Map<String, String> env = System.getenv();
        System.out.println(env.get("JAVA_HOME"));

        // 系统属性
        Properties properties = System.getProperties();
        System.out.println(properties.getProperty("os.name"));

        // 获取系统时间, 还有一个纳秒的...
        long current = System.currentTimeMillis();
        System.out.println(current);

        // 根据引用地址计算 hash 值
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        String s3 = "Hello";
        String s4 = "Hello";
        System.out.println("s1: " + System.identityHashCode(s1) + ", " + s1.hashCode());
        System.out.println("s2: " + System.identityHashCode(s2) + ", " + s2.hashCode());
        System.out.println("s3: " + System.identityHashCode(s3) + ", " + s3.hashCode());
        System.out.println("s4: " + System.identityHashCode(s4) + ", " + s4.hashCode());
    }

    private static void runtimeTest() {
        // 获取运行时信息
        Runtime rt = Runtime.getRuntime();
        System.out.println("处理器可用数: " + rt.availableProcessors());
        System.out.println("空闲内存: " + rt.freeMemory() / (1024 * 1024) + "MB");
        System.out.println("总内存: " + rt.totalMemory() / (1024 * 1024) + "MB");
        System.out.println("最大内存: " + rt.maxMemory() / (1024 * 1024) + "MB");

        // 执行命令
        try {
            Process process = rt.exec("notepad.exe");
            process.waitFor();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
