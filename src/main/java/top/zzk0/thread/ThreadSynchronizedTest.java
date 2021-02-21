package top.zzk0.thread;

/*
线程同步有三大类:
1. 同步方法, 用 synchronized 来修饰方法
2. 同步代码块 synchronized(...), ... 表示同步对象, 只有一个线程可以访问
3. Lock, ReentrantLock(可重入锁)
*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadSynchronizedTest {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        new ConsumerThread(bq).start();
        bq.put("Java");
        bq.put("Java1");
        bq.put("Java2");
        System.out.println("ok");
    }
}

class ConsumerThread extends Thread {
    BlockingQueue<String> bq;

    public ConsumerThread(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                String x = bq.poll();
                System.out.println(x);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
