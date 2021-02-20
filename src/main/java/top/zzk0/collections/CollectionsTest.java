package top.zzk0.collections;

/*
下面是 Java 中集合相关的几个重要的接口

Collection(子接口: Set, Queue, List)
Map
Iterator(hasNext, next, remove 用这个移除上一次获取的元素不会引发异常)

用 Iterator 或者 foreach 的时候, 不能用 Collection 本身的 remove 接口去移除元素,
否则会引发异常 ConcurrentModificationException

后面实现了一个斐波那契数列, 实现一个 Iterator<Integer> 接口
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionsTest {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
            if (integer == 1) {
                iterator.remove();
            }
//            if (integer == 1) {
//                collection.remove(1); // 如果是 2 那么不会引起异常
//            }
        }
        System.out.println("----------------------");
        for (Integer integer : collection) {
            System.out.println(integer);
        }

        // 斐波那契数列
        System.out.println("----------------------");
        Fibonacii fibonacii = new Fibonacii();
        Iterator<Integer> fiboIter = fibonacii.iterator();
        for (int i = 0; i < 10; i++) {
            if (fiboIter.hasNext()) {
                System.out.println(fiboIter.next());
            }
        }

        // 自然数
        System.out.println("----------------------");
        Iterator<Integer> naturalNumber = new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < Integer.MAX_VALUE;
            }

            @Override
            public Integer next() {
                return i++;
            }
        };
        for (int i = 0; i < 10; i++) {
            if (naturalNumber.hasNext()) {
                System.out.println(naturalNumber.next());
            }
        }
    }
}

class Fibonacii implements Iterator<Integer>, Iterable<Integer> {

    Integer i = 0;
    Integer j = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int temp = i + j;
        i = j;
        j = temp;
        return i;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }
}
