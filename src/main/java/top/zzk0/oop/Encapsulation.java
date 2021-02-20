package top.zzk0.oop;

/*
封装访问权限:
default 不加任何修饰符
下面的 InPackage 在包外就访问不到了, Encapsulation 的 answer 也访问不到
具体可以到 oop.test 里去试试 oop 和 oop.test 是两个不同的包

            private     default     protected       public
同个类中        1           1            1              1
同个包中                    1            1              1
子类中(可以在不同包)                       1              1
全局范围                                                1
*/

public class Encapsulation {
    public static int question; // 包外可访问
    static int answer; // 包外不可访问
}

class InPackage {
    void test() {
        Encapsulation.answer = 1;
    }
}