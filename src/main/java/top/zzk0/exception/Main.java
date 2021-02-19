package top.zzk0.exception;

/*
首先需要搞清楚, checked 异常和 runtime 异常
checked 异常必须要显式处理, 否则不能通过编译; runtime 异常则不用, 自定义异常用 runtime 就可以避免繁琐的编码处理了

Java7 中对异常进行了两个增强:
1. 自动关闭资源, try 中可以打开多个资源, 这些资源必须实现 Closeable 接口, 然后可以避免 finally 中手动 close 了
2. catch 中 throw 异常, 方法前面可以声明更加具体
3. 同时捕获多个异常, 当时 catch 之后, 会隐式修饰 final, 正常的 catch 不会

Throwable 中的常用方法:
getMessage()
printStackTrace()

finally 中 return 会有异常, 具体看自己的博客

一个设计模式: 异常转义, 比如底层抛出的 IO 异常, 不应该给上层看到, 这时可以先自定义别的异常, 然后再往上抛
当然, 可以将异常封装到异常中, 调用构造器 Throwable(Throwable cause) 即可
*/

import java.io.*;

public class Main {

    // 基类
    Throwable throwable;

    // 错误
    Error error;
    IOError ioError;
    ThreadDeath threadDeath;

    // 异常
    Exception exception;
    IOException ioException;
    RuntimeException runtimeException; // 不需要显式处理, 这之外的异常叫 checked 异常, 需要代码处理

    // 下面都是 RuntimeException 子类
    IndexOutOfBoundsException indexOutOfBoundsException;
    NullPointerException nullPointerException;
    ClassCastException classCastException;

    public static void main(String[] args) {
        // Java7 特性展示
        try(FileInputStream fis = new FileInputStream(new File("a.txt"));
            FileOutputStream fos = new FileOutputStream(new File("b.txt"))) {
            byte[] buffer = new byte[1024];
            fis.read(buffer);
            fos.write(buffer);
        }
        catch (IOException|RuntimeException e) {
            e.printStackTrace();
            // e = new IndexOutOfBoundsException(); // 不能赋值
        }
        catch (Exception e) {
            e = new IndexOutOfBoundsException();
        }
    }
}
