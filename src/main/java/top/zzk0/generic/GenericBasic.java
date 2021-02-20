package top.zzk0.generic;

/*
介绍基本的使用.

List 是一个泛型接口, 内部用 Object 来存储, 所以如果不用泛型, 直接使用 List 类型,
那么这个 List 既可以存储 String, 又可以存储 Integer

Java7 中引入了菱形语法, 泛型 new 的时候就不需要多余的参数了
List<String> list = new List<>();

下面有两个例子, 自定义了一个带有泛型的类, 继承泛型类

泛型的常见错误用法:
1. 静态形参. 泛型不能声明为静态成员, 方法中的参数 void method(T arg), 不过这个是合法的 void method(Class<T> arg)
2. instanceof List<Integer>
3. 泛型并没有子类. List<Number> 并不是 List<Integer> 的父类, 不能将后者复制给前者
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericBasic {

    public static void main(String[] args) {
        // 例子1: List 本质上存储的都是 Object, 所以兼容 String 和 Integer
        List list = new ArrayList();
        list.add("asdf");
        list.add("assd");
        list.add(11);
        list.add(22);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 例子2: 介绍自定义泛型类
        Apple<String> apple1 = new Apple<>("asdf");
        Apple<Integer> apple2 = new Apple<>(2333);

        // 例子3: 继承泛型类
        // 错误写法, 虽然继承了, 但它不是泛型类: Pineapple<String> pineapple = new Pineapple("asdf");
        Pineapple pineapple = new Pineapple("asdfasdf");
        System.out.println(pineapple.getInfo());

        // 例子4: 泛型无子类, List<Object> 不是 List<String> 的父类, 虽然 Object 是 String 的父类
        // List<Integer> integerList = new LinkedList<>();
        // List<Number> numberList = integerList; // 赋值失败
        // Integer[] x = new Integer[]{1, 2, 3, 4, 5};
        // Number[] y = x;
        // y[0] = 0.5; // ArrayStoreException, 数组这么做是不安全的, 所以改进 List 不允许那么赋值

        // 例子5: 设定类型通配符的上限
        List<Integer> numbers1 = new LinkedList<>();
        List<Double> numbers2 = new LinkedList<>();
        List<String> numbers3 = new LinkedList<>();
        print(numbers1);
        print(numbers2);
        // print(numbers3); 报错, 因为 List<String> 不能传入 List<? extends Number>

        // 例子6: 设定类的形参上限
        Orange<Double> orange1 = new Orange<>();
        Orange<Integer> orange2 = new Orange<>();
        Orange<Number> orange3 = new Orange<>();
        // Orange<String> orange3; // 非法

        // 例子7: 通配符的使用
        List<Integer> numbers4 = new LinkedList<>();
        List<?> numbers5 = numbers4;
        // numbers5.add(1); // add 任何东西都不行
    }

    private static void print(List<? extends Number> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        // 下面这么做是不允许的, 因为不知道 numbers 的确切类型, 它可以是 List<Integer>
        // numbers.add(0.5);
    }
}

class Apple<T> {
    private T info;

    public Apple(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }
}

class Pineapple extends Apple {
    public Pineapple(String x) {
        super(x);
    }

    public String getInfo() {
        return super.getInfo().toString();
    }
}

class Orange<T extends Number> {

}