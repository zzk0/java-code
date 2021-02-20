package top.zzk0.oop;

/*
建议 JVM 进行 gc 有两种方法: System.gc(), Runtime.getRuntime().gc()
一个对象在内存中有三种状态: 可达, 可恢复(调用 finalize 方法), 不可达(这个就要回收了)
如果进行了垃圾回收(gc), 那么会调用对象的 finalize 方法,
这个 finalize 方法可以使到对象变成可达的, 下面有例子

注意 finalize 是否调用是确定的, 只要 GC 就调用
但是 GC 是不确定的, System.gc() 只能是建议 JVM 进行 GC, 但 JVM 不一定 GC

另外可以使用 runFinalization 方法, 让 JVM 调用可恢复对象的 finalize 方法,
不过还是要先调用 System.gc(), 不然不会执行 finalize 方法
*/

public class GcTest {

    private static GcTest gcTest = null;

    void show() {
        System.out.println("show");
    }

    public static void main(String[] args) throws Exception {
        new GcTest();
        // 方法1
        System.gc();
        Thread.sleep(2000);
        // 方法2
        System.gc();
        System.runFinalization();
        gcTest.show();
    }

    @Override
    protected void finalize() throws Throwable {
        gcTest = this;
    }
}
