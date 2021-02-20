package top.zzk0.oop;

/*
Java 中方法调用, 参数只有值传递
传 int, 只传值
传对象, 也是传值, 只不过这个值指向堆内存中的一块内存

可变参数: 实际和传数组差不多, 可变参数只能在参数列表的最后
*/

public class MethodInvocation {

    public static void swap(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("swap: " + a + " " + b);
    }

    public static void swap(Node node) {
        node.a ^= node.b;
        node.b ^= node.a;
        node.a ^= node.b;
        System.out.println("swap: " + node.a + " " + node.b);
    }

    public static void show(int i, String... args) {
        System.out.println(args[i]);
    }

    public static void main(String[] args) {
        // 例子1: swap
        int a = 10, b = 7;
        System.out.println("start: " + a + " " + b);
        swap(a, b);
        System.out.println("end: " + a + " " + b);

        // 例子2: swap 引用中的内容, 可以 swap
        Node node = new Node();
        node.a = 10; node.b = 7;
        System.out.println("start: " + node.a + " " + node.b);
        swap(node);
        System.out.println("end: " + node.a + " " + node.b);

        // 例子3: 可变参数
        show(0, "asdf", "iiii");
        show(1, "asdf", "iiii");
        show(2, "asdf", "iiii", "asdfasdf");
    }
}

class Node {
    int a;
    int b;
}
