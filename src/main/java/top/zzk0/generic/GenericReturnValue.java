package top.zzk0.generic;

/*
在用 PhantomReference 的时候, 发现 poll 的返回值接受不了, 特此实验一下
结论: <? extend T>, <? super T> 作为返回值的, 如果变量不是声明相同, 那么需要强制转化

结果发现, 需要强制转化一下:
理由是: Xxx<? extends T> 这个东西可能是多种多样的, 那么具体是哪一个呢?
下面的例子中 getPage, 可以是 Page<Grandfather>, Page<Father>, Page<Son>
是哪一个, 不确定, 因此我们需要自己手动转一下

XXX<? super T> 也一样, 因为 T 可能有父类, 父类的父类... 因此是哪一个就不能确定, 需要自己转一下
*/

public class GenericReturnValue {

    public static void main(String[] args) {
        Book<GrandFather> book = new Book<>();
        Page<GrandFather> page = (Page<GrandFather>) book.getPage();
        Page<Father> page1 = (Page<Father>) book.getPage();
        Page<Son> page2 = (Page<Son>) book.getPage();
        Page<? extends GrandFather> page10 = book.getPage();

        Book<Son> book1 = new Book<>();
        Page<GrandFather> page3 = (Page<GrandFather>) book1.getSonPage();
        Page<Father> page4 = (Page<Father>) book1.getSonPage();
        Page<Son> page5 = (Page<Son>) book1.getSonPage();
        Page<? super Son> page11 = book1.getSonPage();

        Book<Father> book2 = new Book<>();
        // Page<GrandFather> page6 = (Page<GrandFather>) book2.getPage(); // 上界是 Father, 不能转
        Page<GrandFather> page7 = (Page<GrandFather>) book2.getSonPage();
        Page<Son> page8 = (Page<Son>) book2.getPage();
        // Page<Son> page9 = (Page<Son>) book2.getSonPage(); // 下界是 Father, 不能这么转
    }

}

class Page<T> {
    T info;
}

class Book<T> {
    public Page<? extends T> getPage() {
        return new Page<T>();
    }

    public Page<? super T> getSonPage() {
        return new Page<>();
    }
}

class GrandFather {}

class Father extends GrandFather {}

class Son extends Father {}
