package top.zzk0.thread;

/*
测试 CoW: Copy on Write 是写时复制的意思
CoW 是一种资源管理策略, 当复制一个资源的时候, 没有必要真正进行复制, 只有在副本需要修改的时候, 才真正执行复制

那么 CopyOnWriteArrayList 是这个意思吗? 似乎和书上看到的不太一样

注意看源码就好了

*/


/*
clone() 函数: 实际上调用的父类(Object)的 clone, 实际上执行的是浅拷贝

* Returns a shallow copy of this list.  (The elements themselves
* are not copied.)
*
* @return a clone of this list

public Object clone() {
    try {
        @SuppressWarnings("unchecked")
        CopyOnWriteArrayList<E> clone =
            (CopyOnWriteArrayList<E>) super.clone();
        clone.resetLock();
        return clone;
    } catch (CloneNotSupportedException e) {
        // this shouldn't happen, since we are Cloneable
        throw new InternalError();
    }
}
*/


/*
add() 函数: 可以看到每次写入都需要复制数组, 然后再写入

* Appends the specified element to the end of this list.
*
* @param e element to be appended to this list
* @return {@code true} (as specified by {@link Collection#add})
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
*/

public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
    }

}
