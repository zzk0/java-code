package top.zzk0.oop;

public class CloneVisible {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        // queue.clone(); // 不能这么调用的原因是: protected 的 clone 并不和 MyQueue 在同个包
        queue.test();
    }

}

class MyVector {
    protected void test() {}
}

class MyQueue extends MyVector {

}

class MyStack {

}
