package top.zzk0.collections;

/*
Queue 有三个实现类, 一个子接口
子接口: Deque, 双端队列, 可以当成 Stack 来用了
PriorityQueue, 优先队列, 排序方式和 TreeSet 一样, 自然排序 and 定制排序
ArrayDeque, 基于数组的双端队列, 随机访问性能比 LinkedList 好, 插入删除则差一些
LinkedList, 基于链表的双端队列, List, 随机访问差
*/

import java.util.Deque;
import java.util.LinkedList;

public class QueueTest {

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(10); // LinkedList 内部是调用 addFirst
        stack.push(11);
        stack.push(12);
        // 12 11 10, 因此后面 pollLast 是 10, pollFirst 的话会是 11
        System.out.println(stack.pop());
        System.out.println(stack.pollLast());
    }
}
