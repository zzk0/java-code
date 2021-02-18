package top.zzk0.reflect;

import java.net.URL;

public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
        ClassLoader rootClassLoader = extClassLoader.getParent();
        System.out.println(rootClassLoader); // null, 根类加载器不是 java 实现的, 这里为 null 不代表不存在
    }
}
