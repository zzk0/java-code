package top.zzk0.oop;

/*
Java 中有四种引用类型:
强引用, 软引用, 弱引用, 虚引用
强引用: 对象赋值给一个引用变量
软引用: 只有当内存不足的时候才回收
弱引用: 发生 gc 的时候回收
虚引用: 不存储引用, 也不能获取引用, 一般用来跟踪对象是否被垃圾回收

有一个东西叫 ReferenceQueue, 创建上述引用的时候, 可以通过构造器传入
当上述引用的内容被回收之后, 就会出现在 ReferenceQueue 中

输出:
asdf
null
reference == wr ? true
null
null
reference1 == pr ? true
*/

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    public static void main(String[] args) throws Exception {
        // 弱引用
        ReferenceQueue<String> rq1 = new ReferenceQueue<>();
        WeakReference<String> wr = new WeakReference<>(new String("asdf"), rq1);
        System.out.println(wr.get());
        System.gc();
        Thread.sleep(2000);
        System.out.println(wr.get());
        Reference<? extends String> reference = rq1.poll();
        System.out.println("reference == wr ? " + (reference == wr));

        // 虚引用
        PhantomReference<String> pr = new PhantomReference<>(new String("zxcv"), rq1);
        System.out.println(pr.get());
        System.gc();
        Thread.sleep(2000);
        System.out.println(pr.get());
        Reference<? extends String> reference1 = rq1.poll();
        System.out.println("reference1 == pr ? " + (reference1 == pr));
    }

}
