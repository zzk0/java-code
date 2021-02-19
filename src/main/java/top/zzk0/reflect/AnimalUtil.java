package top.zzk0.reflect;

public class AnimalUtil {

    private static int count;

    public static void method1() {
        System.out.println("---------start--------- 第 " + count + " 次调用");
        count++;
    }

    public static void method2() {
        System.out.println("---------end---------");
    }
}
