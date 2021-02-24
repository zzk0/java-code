package top.zzk0.java8;

/*
函数式接口: 一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
*/

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {
        SaySomething saySomething = message -> System.out.println(message);
        SaySomething saySomething1 = System.out::println;

        // java.util.function 下面封装了很多函数, 比如
        // 接受一个参数, 返回一个布尔值的: Predicate
        List<Double> list1 = Arrays.asList(0.1, 0.2, 0.5, 0.8);
        eval(list1, item -> item > 0.5);

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
        eval(list2, item -> item % 2 == 0);
    }

    public static <T> void eval(List<T> list, Predicate<T> predicate) {
        for (T item : list) {
            if (predicate.test(item)) {
                System.out.println(item);
            }
        }
    }
}

@FunctionalInterface
interface SaySomething {
    void say(String message);
    // void talk(); // 不能声明两个抽象方法, 否则不给注解
}
