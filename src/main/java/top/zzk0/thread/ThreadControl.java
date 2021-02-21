package top.zzk0.thread;

/*
线程控制方法:

join: 让当前线程等待被 join 线程执行完毕
后台线程: 当前台线程执行完毕, 后台线程自动结束, gc 就是一个后台线程, setDaemon
sleep: 让线程等待, 进入阻塞
yield: 主动让出处理器资源
线程优先级: setPriority
*/

public class ThreadControl extends Thread {

    public static void main(String[] args) throws Exception {
        ThreadControl thread = new ThreadControl();
        thread.setDaemon(true);
        thread.join();
        thread.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        Thread.yield();
    }
}
