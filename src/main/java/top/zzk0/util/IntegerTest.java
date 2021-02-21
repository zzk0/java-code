package top.zzk0.util;

public class IntegerTest {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        MyQueue<Integer> queue1 = (MyQueue<Integer>)queue.clone();
        System.out.println(queue.poll());
        System.out.println(queue1.poll());
        System.out.println(queue.poll());

        // 以上会输出什么呢?
        // clone 只复制了值, 也就是 queue 和 queue1 中的每个成员的引用相同
        // queue 执行 poll, 会将 j = j + 1, 因此你可能会觉得两个 queue 中的 j 都是 1 了
        // 但是, 包装类是具有不可变性的... 这意味着 j = j + 1 这个语句, 并不是改变 j 内部的值,
        // 而是改变了 j 的引用来改值. (不可变性, 可以去看看 Integer, 它存储值用了 final)

        // 因为不可变性的存在, 所以 queue.poll() 之后 [queue.j = 1, queue1.j = 0]
        // queue1.poll() [queue.j = 1, queue1.j = 1]
        // 输出: 1 1 2
    }

}

class MyQueue<E> implements Cloneable {
    Integer i = 0;
    Integer j = 0;
    Object[] elements;

    public MyQueue() {
        elements = new Object[10];
    }

    public void offer(E e) {
        elements[j++] = e;
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        return (E)elements[i++];
    }

    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            MyQueue<Integer> clone = (MyQueue<Integer>)super.clone();
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    };
}
