package top.zzk0.java8;

/*
流式处理, 这个教程真是太好了, 简明扼要: https://www.runoob.com/java/java8-streams.html

生成流: stream(), parallelStream()
逐个处理: forEach() 方法
操作: filter, map, sorted, limit, Collectors 工具类
*/

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println("元素总个数: " + list.parallelStream().count());
        System.out.println("大于 1 的个数: " + list.parallelStream().filter(a -> a > 1).count());
        System.out.println("正序排序");
        list.stream()
                .sorted((Integer a, Integer b) -> a.compareTo(b))
                .map(a -> a.toString() + " ")
                .forEach(System.out::print);
        System.out.println("\n倒序排序");
        list.stream()
                .sorted((Integer a, Integer b) -> b.compareTo(a))
                .map(a -> a.toString() + " ")
                .forEach(System.out::print);
        int value = list.stream()
                .filter(a -> a > 1)
                .mapToInt(a -> a)
                .summaryStatistics()
                .getMin();
        System.out.println("\n最小的大于 1 的数: " + value);
    }
}
