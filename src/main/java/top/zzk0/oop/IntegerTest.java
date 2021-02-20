package top.zzk0.oop;

/*
Integer 一个现象的展示
Integer 缓存的范围: -128 ~ 127
*/

public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 1; // 自动装箱
        Integer b = 1;
        Integer c = 128; // 没有被缓存的 Integer
        Integer d = 128;
        System.out.println("a == b ? " + (a == b));
        System.out.println("c == d ? " + (c == d)); //
    }

}
