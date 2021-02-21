package top.zzk0.thread;

/*
线程创建只有一种方式, 那就是 Thread, 但是线程运行的内容, 可以有三个东西:
1. Thread 覆盖 run 方法
2. Runnable 覆盖 run 方法, 然后传入 Thread
3. Callable 覆盖 call 方法, 传入一个 Runnable 子类 FutureTask, 之后再传入 Thread,
   这个方法可以通过 FutureTask 获取返回值
*/

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Creation {

    public static void main(String[] args) throws Exception {
        Thread thread0 = null, thread1 = null;
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            if (i == 10) {
                PrintRunnable runnable = new PrintRunnable();
                thread0 = new Thread(runnable);
                thread0.start();
            }
            if (i == 20) {
                thread1 = new PrintThread();
                thread1.start();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

        // 等待所有线程结束
        thread0.join();
        thread1.join();

        // 自己计算 1 ~ 50 的和, 开线程计算 50 ~ 100 的和
        int sum0 = 0, sum1 = 0;
        Callable<Integer> sumCallable = new SumCallable(50, 100);
        FutureTask<Integer> task = new FutureTask<>(sumCallable);
        new Thread(task).start();
        for (int i = 0; i < 50; i++) {
            Thread.sleep(100);
            sum0 += i;
        }
        System.out.println("sum 1 to 50 is: " + sum0);
        sum1 = task.get();
        System.out.println(sum0 + sum1);
    }
}

class PrintThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                System.out.println(getName() + " " + i);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintRunnable implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SumCallable implements Callable<Integer> {

    int start;
    int end;

    public SumCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            Thread.sleep(50);
            sum += i;
        }
        System.out.printf("sum %d to %d is: %d\n", start, end, sum);
        return sum;
    }
}
