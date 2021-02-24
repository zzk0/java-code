package top.zzk0.java8;

/*
方法引用:
通过方法的名字来指向一个方法, 用 [类名|对象名]::[方法|new] 指向一个方法
后面是 new 指向构造器
*/

import java.util.ArrayList;
import java.util.List;

public class FunctionalReference {

    public static void main(String[] args) {
        // 不能在静态方法中指向非静态方法, 所以进行一下实例化
        Functions functions = new Functions();
        MathOperation plus = functions::plus;

        // 将 System.out.println 作为方法引用
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) list.add(i);
        list.forEach(System.out::println);

        // 获取构造器
        Car car = Car.create(Car::new);
    }
}

class Functions {
    int plus(int a, int b) {
       return a + b;
    }
}

interface Constructor<T> {
    T get();
}

class Car {
    public static Car create(final Constructor<Car> constructor) {
        return constructor.get();
    }
}
