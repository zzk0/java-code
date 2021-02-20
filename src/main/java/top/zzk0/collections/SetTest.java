package top.zzk0.collections;

/*
HashSet         : 散列表, 无序, 元素相等标准: equals && hashCode 相等, 用 hashCode 决定位置
LinkedHashSet   : 额外维护链表来维护 *插入次序*, 插入删除慢一点, 遍历会快一点
TreeSet         : 红黑树, 有序, 元素相等标准: compareTo 相等, 不考虑 equals, 默认升序排序
EnumSet         : 专门为枚举类设计的, 方法 allOf, noneOf, of, copyOf ...

这个部分需要注意几个东西: equals, hashCode, Comparable(自然排序), Comparator(定制排序)
TreeSet 的元素要么实现了 Comparable, 要么初始化 TreeSet 传入一个 Comparator, 否则 TreeSet 不知道如何比较元素

创建线程安全的 Set: Set<Integer> safeSet = Collections.synchronizedSet(set);
*/

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

    public static void main(String[] args) {
        // TreeSet 两种使用方式
        TreeSet<Node> set1 = new TreeSet();
        set1.add(new Node());
        set1.add(new Node());

        // 降序排序
        TreeSet<Integer> set2 = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        set2.add(100);
        set2.add(1);
        set2.add(150);
        set2.add(-100);
        System.out.println(set2);

        // java8 lambda
        TreeSet<Integer> set3 = new TreeSet<>((o1, o2) -> -o1.compareTo(o2));

        // 线程安全
        Set<Integer> safeSet = Collections.synchronizedSet(set3);
    }

}

class Node implements Comparable<Node> {

    int x;
    int y;

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}

class Leaf {

}
