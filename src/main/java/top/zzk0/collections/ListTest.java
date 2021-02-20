package top.zzk0.collections;

/*
List 接口的具体实现有几个:
ArrayList       : 普普通通, 线程不安全
Vector          : 比较老, 线程安全, 性能稍微差点
Stack           : 继承 Vector 来实现栈的操作
LinkedList      : 继承 Deque, 完全可以替代 Stack
Arrays.asList   : 长度固定的 ArrayList, 不可以 add remove

List 还提供了一个 ListIterator, 在 Iterator 的基础上增加了:
hasPrevious, previous, add
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
            listIterator.add(99); // 并没有马上奏效, hasNext 看不到的
        }

        // 反向遍历
        System.out.println("--------------------------");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
