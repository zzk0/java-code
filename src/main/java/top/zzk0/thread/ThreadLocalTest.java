package top.zzk0.thread;

/*
ThreadLocal 线程专属的局部变量, 每个对象在每个线程会有一个副本,
这个线程对 ThreadLocal 的修改, 另一个线程看不见

下面的例子中, 谁最后修改了 name1, calculator 在哪个线程上的 name1 都是那个,
name 就不同, name 每个线程不一样
*/

public class ThreadLocalTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        new Thread(calculator).start();
        new Thread(calculator).start();
        new Thread(calculator).start();
        try {
            Thread.sleep(2000);
            System.out.println(calculator.name.get()); // 主线程也不可见
            System.out.println(calculator.name1); // 最后设置的那个
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Calculator implements Runnable {

    ThreadLocal<String> name = new ThreadLocal<>();
    String name1;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 8; i++) {
                Thread.sleep(100);
                if (i == 3) {
                    name.set(Thread.currentThread().getName());
                }
                if (i == 5) {
                    name1 = Thread.currentThread().getName();
                }
                System.out.printf("name: %s, name: %s\n", name.get(), name1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
