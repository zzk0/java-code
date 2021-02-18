package top.zzk0.reflect;

/*
类加载, 连接, 初始化
加载: 将 class 文件读取内存
连接: 将二进制数据整合到 JRE, 步骤包括: 验证, 准备, 解析
初始化: 对静态域初始化, 初始化的时机: 用到静态的变量方法都要初始化, Class.forName 也要, 主类

初始化有一种特殊情况, 看 FinalFieldClass, 编译时可以确定的常量
如果编译时还不能确定, 那还是不能替换, 需要初始化

对于 ClassLoader, loadClass 方法仅加载类, 不做初始化
Class.forName 方法才会强制初始化
*/

public class Initialization {

    public static void main(String[] args) throws Exception {
//        System.out.println(FinalFieldClass.TIME);
//        System.out.println(FinalFieldClass.TEXT);
//        System.out.println(FinalFieldClass.text1);
//        FinalFieldClass.show();

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        cl.loadClass("top.zzk0.reflect.FinalFieldClass");
        System.out.println("FinalFieldClass 加载完毕");
        Class.forName("top.zzk0.reflect.FinalFieldClass");
    }

}

class FinalFieldClass {
    static {
        System.out.println("初始化代码块 FinalFieldClass");
    }
    public static final String TEXT = "编译时确定的东西, 会直接替换, 而不是继续做一个变量";
    public static final String TIME = System.currentTimeMillis() + "";
    public static String text1 = "用到这个 text1 就需要初始化了";

    public static void show() {
        System.out.println(TEXT);
    }
}