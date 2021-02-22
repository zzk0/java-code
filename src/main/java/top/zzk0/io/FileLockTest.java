package top.zzk0.io;

/*
https://stackoverflow.com/questions/9906161/why-does-overlappingfilelockexception-happen-when-locking-a-file

文件锁异常: OverlappingFileLockException
原因是, 这里文件锁并不像多线程中的锁, 这个锁只能给一个线程, 另一个线程再次调用 lock, 那么就会异常
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.ReentrantLock;

public class FileLockTest extends Thread {

    private final ReentrantLock lock;

    public FileLockTest(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        String name = currentThread().getName();
        long sleepTime = (long) (Math.random() * 1000);
        System.out.println(name + " 开始睡觉 " + sleepTime);
        try {
            Thread.sleep(sleepTime);
            System.out.println(name + " 醒了, 准备获取文件锁");
            URL url = ClassLoader.getSystemResource("1.txt");
            File file = new File(url.getPath());
            try (FileChannel channel = new FileOutputStream(file).getChannel()) {
                System.out.println(name + " 临界区外");
                lock.lock();

                System.out.println(name + " 临界区内");
                FileLock fileLock = channel.lock(); // 这个锁不能同时加
                System.out.println(name + " 获得了锁");
                Thread.sleep(1000);
                fileLock.release();
                Thread.sleep(1000);
                System.out.println(name + " 释放了锁");

                lock.unlock();
            }
            System.out.println(name + " 终止了");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new FileLockTest(lock).start();
        new FileLockTest(lock).start();
        new FileLockTest(lock).start();
        System.out.println(Thread.currentThread().getName() + " 终止了");
    }
}
