package top.zzk0.io;

/*
展示 File 的基本用法, 它其实是文件和目录的抽象, 目录也是一个 File
这个文件主要探索 Maven 项目中, 如何读取 resources 下的文件
系统类加载器的目录其实是在 target/classes 下
*/

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class FileTest {

    public static void main(String[] args) throws IOException {
        // 例子1: 当前所在目录
        File file = new File(".");
        System.out.println("当前目录: " + file.getAbsolutePath());

        // 例子2: 获取 resources 中的 1.txt
        URL url = FileTest.class.getClassLoader().getResource("1.txt");
        URL url1 = FileTest.class.getClassLoader().getResource("dir/2.txt");
        System.out.println("1.txt: " + url);
        System.out.println("dir/2.txt: " + url1);

        // 例子3: 通过相对路径获取 resources 中的 1.txt
        File file1 = new File("./target/classes/1.txt");
        System.out.println("1.txt: " + file1.getAbsolutePath());

        // 例子4: 通过 url 获取 resources 中的 dir/2.txt
        File file2 = new File(url.getPath());
        System.out.println("2.txt: " + file2.getAbsolutePath());

        // 例子5: ClassLoader 的路径, 系统类加载器
        // 如果没有指定 CLASSPATH, 默认以当前路径作为加载路径
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = FileTest.class.getClassLoader();
        System.out.println("FileTest 是否由系统类加载器进行加载? "
                + systemClassLoader.equals(classLoader));
        Enumeration<URL> urlEnumeration = systemClassLoader.getResources("");
        while (urlEnumeration.hasMoreElements()) {
            System.out.println(urlEnumeration.nextElement());
        }
        // 两者相等, 因此 getResource 是在类路径中去寻找

        // 例子6: 文件过滤器
        URL url2 = systemClassLoader.getResource("dir");
        File file3 = new File(url2.getPath());
        String[] filenames = file3.list((dir, name) -> name.endsWith(".java"));
        System.out.print("过滤器: ");
        for (String filename : filenames) {
            System.out.print(filename + " ");
        }
        System.out.println();
        System.out.print("不加过滤器: ");
        for (String filename : file3.list()) {
            System.out.print(filename + " ");
        }
        System.out.println();
    }
}
