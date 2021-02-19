package top.zzk0.util;

/*
Object 常用方法:
equals 判断两个对象是否相等, 一般是判断地址是否相等
hashCode 散列值计算, 可以根据地址来算, 也可以覆盖自己写一个 hashCode 方法
clone 方法, 浅拷贝, 提供了一个 Cloneable 接口

Objects 提供一些 "空指针" 安全的方法:
Objects.hashCode(obj), 这样即使 obj 是 null, 这么调用也是安全的了
还有 toString(obj), requireNonNull(obj)...
*/

public class ObjectTest {

    public static void main(String[] args) {
        // 据说, clone 比 copy 方法快两倍
        final int LENGTH = 10_000_000;
        int[] arr = new int[LENGTH];
        long t0 = method0(arr);
        long t1 = method1(arr);
        long t2 = method2(arr);
        System.out.println("clone: " + t0);
        System.out.println("System.arraycopy.: " + t1);
        System.out.println("Manual copy: " + t2);

        /* 结果: emm, 我还能说什么呢, 用 arraycopy 就好
        LENGTH = 10_000_000, for 1000 次, 单位 s
        clone: 10
        System.arraycopy.: 7
        Manual copy: 7

        100, 100000, ms
        31 16 16
        人生苦短, 我用 arraycopy
        */
    }

    private static long method0(int[] arr) {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int[] arr1 = arr.clone();
        }
        return (System.currentTimeMillis() - current) / 1000;
    }

    private static long method1(int[] arr) {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int[] arr1 = new int[arr.length];
            System.arraycopy(arr, 0, arr1, 0, arr.length);
        }
        return (System.currentTimeMillis() - current) / 1000;
    }

    private static long method2(int[] arr) {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int[] arr1 = new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                arr1[j] = arr[j];
            }
        }
        return (System.currentTimeMillis() - current) / 1000;
    }

}
