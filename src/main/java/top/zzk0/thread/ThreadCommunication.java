package top.zzk0.thread;

/*
线程通信:
对于一个 Object, 这三个方法必须由同步监视器来调用:
wait()          : 让当前线程等待, 进入阻塞, 其他线程可以进入同步代码块了
notify()        : 唤醒这个对象上一个 wait() 的线程, 随机选择
notifyAll()     : 唤醒这个对象上所有 wait() 的线程

一个对象的方法, 可能同时有多个线程在访问, 只要调用 wait, 就可以让那个方法等待,
调用这个对象上的 notify, 就可以唤醒其中一个

对于一个 Lock, 需要配合 Condition 类:
await()
signal()
signalAll()

阻塞队列:
BlockingQueue
*/

public class ThreadCommunication {

    public static void main(String[] args) throws Exception {
        final Apple apple = new Apple();
        new AppleThread(apple).start();
        new AppleThread(apple).start();
        new AppleThread(apple).start();
        Thread.sleep(1000);
        // apple.notify(); // 不可以这么调用, 必须加上同步代码块, IllegalMonitorStateException
        synchronized (apple) {
            apple.notify();
            apple.notify(); // 虽然是 notify 了两次, 只有当这个同步代码块出来之后, 才唤醒两个
        }
        Thread.sleep(1000);
        System.out.println("notifyAll");
        synchronized (apple) {
            apple.notifyAll();
        }
    }
}

class Apple {
    synchronized void show() {
        System.out.println("sleep " + Thread.currentThread().getName());
        try {
            Thread.sleep(100);
            wait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("wake "  + Thread.currentThread().getName());
    }

    synchronized void wake() {
        notify();
    }
}

class AppleThread extends Thread {
    final Apple apple;

    public AppleThread(Apple apple) {
        this.apple = apple;
    }

    @Override
    public void run() {
        apple.show();
    }
}
