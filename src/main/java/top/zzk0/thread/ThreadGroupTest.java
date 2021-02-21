package top.zzk0.thread;

/*
线程组 ThreadGroup, 批量操作线程
*/

public class ThreadGroupTest {

    public static void main(String[] args) {
        ThreadGroup mainTg = Thread.currentThread().getThreadGroup();
        ThreadGroup tg = new ThreadGroup("group-1");
        ThreadGroup tg1 = new ThreadGroup(tg, "group-2");
        new TempThread(mainTg, "thread-1").start();
        new TempThread(mainTg, "thread-2").start();
        mainTg.list();
    }
}

class TempThread extends Thread {

    public TempThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        // Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("oops");
        float x = 1 / 0;
        System.out.println("可以执行到这里吗?");
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " 出现了异常: " + e);
    }
}
