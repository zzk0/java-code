package top.zzk0.java8;

/*
Lambda 函数的使用方法:

格式: (parameters) ->{ statements; }
约束:
参数不强制声明类型;
如果只有一个的参数/语句话, 小括号/大括号可选;
只有一个语句, 可以直接作为返回值;
多个语句, 用 return 做返回值

需要注意 Lambda 函数的作用域:
Lambda 外的局部变量不能修改, 或者显式声明为 final, 局部变量不能和参数名字一样
*/

public class LambdaTest {

    public static void main(String[] args) {
        // 要么同时声明类型, 要么不声明
        MathOperation plus = (int a, int b) -> a + b; // 还可以直接赋值 Integer::sum;
        MathOperation minus = (int a, int b) -> { return a - b; };
        MathOperation multiply = (a, b) -> a * b;
        MathOperation division = (a, b) -> { return a / b; };

        System.out.println("10 + 5 = " + plus.operation(10, 5));
        System.out.println("10 - 5 = " + minus.operation(10, 5));
        System.out.println("10 * 5 = " + multiply.operation(10, 5));
        System.out.println("10 / 5 = " + division.operation(10, 5));

        // 无参数, 括号必须的
        VoidOperation sayHello = () -> System.out.println("Hello");
        sayHello.show();

        // 作用域测试
        int num = 10;
        PlusNumber plusTen = a -> (a + num);
        System.out.println(plusTen.operation(13));
        // num = 5; 不给修改, 要么显式修改
    }
}

interface MathOperation {
    int operation(int x, int y);
}

interface PlusNumber {
    int operation(int x);
}

interface VoidOperation {
    void show();
}