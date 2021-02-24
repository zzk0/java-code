package top.zzk0.java8;

/*
默认方法, 接口中可以带由默认方法, 还能有静态的方法, 不过静态方法不能为空

加入这个方法的好处是, 只用修改接口, 就可以让所有实现类都有一个行为了,
而不需要每个实现类中再去加. 对于, interface -> abstract class -> class
这样的结构, 这个默认方法可以让抽象类直接去掉了.
*/

public class DefaultMethodTest {

    public static void main(String[] args) {
        Heatable.cool();

        Heatable iron = new Iron();
        iron.show();
        iron.heat();
    }
}

interface Heatable {
    default void show() {
        System.out.println("Heatable");
    }

    void heat();

    // 必须要有方法体
    static void cool() {
        System.out.println("cool");
    }
}

class Iron implements Heatable {
    @Override
    public void heat() {
        System.out.println("melt");
    }
}