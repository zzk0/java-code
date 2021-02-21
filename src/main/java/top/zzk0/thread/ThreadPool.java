package top.zzk0.thread;

/*
线程池: Executors 工厂类, 调用静态方法来获取线程池
静态方法返回对象:
ExecutorService: 普通的线程池
ScheduledExecutorService: 可以按照一定的计划执行任务, 比如按一定频率执行, 或者延迟多久后执行

需要提交 Runnable or Callable 对象, 返回值都是一个 Future

Java7 中新增了 ForkJoinPool, 充分利用多核处理器
需要搭配 RecursiveAction(无返回值), RecursiveTask(有返回值) 来使用

经过实验, newFixedThreadPool 也是利用了多核性能的.
分析: 1 个任务, 545ms, 2 个任务还是, 3 个, 4 个都是, 说明不管是哪一种都利用了多核性能

输出:
1个任务, newFixedThreadPool 执行时间: 545ms
1个任务, ForkJoinPool 执行时间: 544ms
2个任务, newFixedThreadPool 执行时间: 539ms
2个任务, ForkJoinPool 执行时间: 541ms
3个任务, newFixedThreadPool 执行时间: 544ms
3个任务, ForkJoinPool 执行时间: 536ms
4个任务, newFixedThreadPool 执行时间: 538ms
4个任务, ForkJoinPool 执行时间: 535ms
5个任务, newFixedThreadPool 执行时间: 1070ms
5个任务, ForkJoinPool 执行时间: 1067ms
6个任务, newFixedThreadPool 执行时间: 1073ms
6个任务, ForkJoinPool 执行时间: 1081ms
7个任务, newFixedThreadPool 执行时间: 1067ms
7个任务, ForkJoinPool 执行时间: 1078ms
8个任务, newFixedThreadPool 执行时间: 1067ms
8个任务, ForkJoinPool 执行时间: 1067ms
*/

import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args)  {
        try {
            for (int i = 1; i < 100; i++) {
                test0(i);
                test1(i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 实际上这个 threads 是任务个数, 线程个数固定 4 个
    static void test0(int threads) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Future[] tasks = new Future[threads];
        long current = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            tasks[i] = pool.submit(new RageSum(50 * i, 50 * (i + 1)));
        }
        int sum = 0;
        for (int i = 0; i < threads; i++) {
            sum += ((Future<Integer>)tasks[i]).get();
        }
        System.out.println(threads + "个任务, newFixedThreadPool 执行时间: "
                + (System.currentTimeMillis() - current) + "ms");
        pool.shutdown(); // 需要 shutdown, 否则一直有前台进程, JVM 不会退出的


    }

    static void test1(int threads) throws ExecutionException, InterruptedException {
        // 使用 ForkJoinPool 来算, 对比一下
        ForkJoinPool pool1 = new ForkJoinPool();
        RageSumOnForkJoinPool[] tasks1 = new RageSumOnForkJoinPool[threads];
        long current = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            tasks1[i] = new RageSumOnForkJoinPool(50 * i, 50 * (i + 1));
            pool1.submit(tasks1[i]);
        }
        int sum = 0;
        for (int i = 0; i < threads; i++) {
            sum += tasks1[i].get();
        }
        System.out.println(threads + "个任务, ForkJoinPool 执行时间: "
                + (System.currentTimeMillis() - current) + "ms");
        pool1.shutdown();
    }

}

class RageSum implements Callable<Integer> {
    int start;
    int end;

    public RageSum(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int sum = 0;
        try {
            for (int i = start; i <= end; i++) {
                Thread.sleep(10);
                sum += i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
}

class RageSumOnForkJoinPool extends RecursiveTask<Integer> {
    int start;
    int end;

    public RageSumOnForkJoinPool(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()  {
        int sum = 0;
        try {
            for (int i = start; i <= end; i++) {
                Thread.sleep(10);
                sum += i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
}
