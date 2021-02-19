package top.zzk0.generic;

/*
直接看例子吧, 如果想要看到警告, 需要自己编译并且加上参数 -Xlint:unchecked
*/

import java.util.*;

public class GenericMethod {

    public static void main(String[] args) {
        // 例子1: 从数组复制到 Collection
        String[] strs = new String[]{"asdf", "zcdd", "sadf"};
        List<String> strList = new ArrayList<>();
        copy(strs, strList);

        Integer[] ints = new Integer[]{1, 2, 5};
        List<Integer> intList = new ArrayList<>();
        copy(ints, intList);

        // 例子2: 泛型构造器
        new Foo(1);
        new Foo("sss");
        new <Integer>Foo(1);
        // new <Integer>Foo("asdfasdf"); // 编译不通过

        // 例子3: 菱形语法不能和泛型构造器混用
        Bar<Integer> bar1 = new Bar<>(1);
        Bar<Integer> bar2 = new <String>Bar<Integer>("asdf");
        // Bar<Integer> bar3 = new <String>Bar<>("asdf"); // 不能混用
        // 编译器提示: java: 无法推断top.zzk0.generic.Bar<E>的类型参数
        //  原因: 不能将 '<>' 与构造器的显式类型参数一起使用, 可是编译器为什么不能推断呢? 笨

        // 例子4: 通配符下限, 注意和上限对比一下
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Number> list2 = new ArrayList<>();
        Integer last = copy1(list2, list1);
        System.out.println(last);

        // 例子5: 类型擦除
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        List list = integerList;
        List<String> stringList = list;
        // System.out.println(stringList.get(0)); // ClassCastException

        // 例子5: 擦除至上界
        Poo<Integer> poo = new Poo<>(10);
        Poo poo1 = poo;
        Number number = poo1.getVal();
        System.out.println(number);
        // Integer number1 = poo1.getVal(); // 编译不通过

        // 例子6: 泛型数组
        // 可以声明, 但是不能 new ArrayList<Integer>[10];
        List<Integer>[] lints; // = new ArrayList<Integer>[10];
        // Object[] objs = (Object[])lints;
        // List<String> list = new ArrayList<>();
        // list.add("boom");
        // objs[0] = list;
        // Integer x = lints[0].get(0); // 发生 ClassCastException

        // 可以赋值一个无泛型的 ArrayList, Unchecked
        lints = new ArrayList[10];
        // Object[] objs = (Object[])lints;
        // List<String> list = new ArrayList<>();
        // list.add("boom");
        // objs[0] = list;
        // Integer x = lints[0].get(0); // 发生 ClassCastException

        /*
        泛型数组那么搞得原因是, Java5 泛型有一个设计原则:
        如果没有出现 unchecked 警告, 那么不会发生 ClassCastException
        前面第一个赋值, 不存在, 如果可以, 将会导致 ClassCastException
        后面那个赋值, 会出现警告, 后面出异常, 还是满足设计要求的
        */

        // 特殊例子, 也不算特殊, 如果要出现异常, 还得自己强制转化才会, get(0) 拿到的是个 Object
        List<?>[] lsa = new ArrayList<?>[100];
        Object[] objs = (Object[])lsa;
        List<String> listOfString = new ArrayList<>();
        listOfString.add("heeeee");
        objs[0] = listOfString;
        System.out.println(lsa[0].get(0));
    }

    private static <T> void copy(T[] src, Collection<T> dest) {
        for (T t : src) {
            dest.add(t);
        }
    }

    // private static <T> T copy1(Collection<T> col1, Collection<? extends T> col2)
    // 如果声明如上, 那么返回的 T, 取决于前者, 如果声明如下, 那么 T 取决于后者
    // 这两个方法不可以同时声明, 因为他们根据参数, 可以同时匹配到两个方法, 冲突了
    private static <T> T copy1(Collection<? super T> col1, Collection<T> col2) {
        T last = null;
        for (T t : col2) {
            last = t;
            col1.add(t);
        }
        return last;
    }
}

class Foo {
    public <T> Foo(T t) {
        System.out.println("Foo Constructor");
    }
}

class Bar<E> {
    public <T> Bar(T t) {
        System.out.println("Bar Constructor");
    }
}

class Poo<T extends Number> {
    T val;

    public Poo(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }
}
