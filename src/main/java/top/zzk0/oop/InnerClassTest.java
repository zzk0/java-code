package top.zzk0.oop;

/*
内部类

类有 5 中成员: Field, 方法, 构造器, 初始化块, 内部类

特别需要注意静态内部类和外部类的成员访问
*/

public class InnerClassTest {

    int property = 10;

    private class InnerClass{
        int property = 11;
        void test() {
            int property = 12;
            System.out.println("外部类 property: " + InnerClassTest.this.property);
            System.out.println("内部类 property: " + this.property);
            System.out.println("局部变量 property: " + property);
        }
    }

    public void onCall() {
        InnerClass innerClass = new InnerClass();
        innerClass.test();
    }

    public static void main(String[] args) {
        // 内部类, 静态内部类, 外部类成员之间的互相访问情况后面

        // 例子1: 内部类的使用
        // InnerClass innerClass = new InnerClass(); // 不可以在 static 中访问非 static 成员(内部类也是)
        new InnerClassTest().onCall();

        // 例子2: new 一个内部类
        Outer outer = new Outer();
        Outer.NormalInner normalInner = outer.new NormalInner();

        // 例子3: 局部内部类
        class MethodInnerClass {}
        class MethodInnerInheritedClass extends MethodInnerClass {}
        MethodInnerClass methodInnerClass = new MethodInnerInheritedClass();

        // 例子4: 匿名内部类, 实例化接口
        final int finalInteger = 1;
        int normalInteger = 2;
        Walkable walkable = new Walkable() {
            @Override
            public void walk() {
                System.out.println("talk");
                System.out.println(finalInteger);
                System.out.println(normalInteger); // 可以访问非 final 局部变量
            }
        };
        walkable.walk();

        // 例子5: 匿名内部类, 实例化抽象类
        Animal animal = new Animal() {
            {
                // 可以使用初始化代码块来初始化, 但是没办法搞一个构造器
                i = 23;
            }
            @Override
            void talk() {
                System.out.println("animal talk");
            }
        };
        animal.show();
    }
}

interface Walkable {
    void walk();
}

abstract class Animal {
    int i = 10;
    void show() {
        System.out.println("animal show " + i);
    }

    abstract void talk();
}



class Outer {
    static int a;
    int b;
    class NormalInner {
        // static int c; // 不能有静态成员
        int d;
        // static void method1() {} // 不能有静态方法
        void method2() {
            Outer.a = 1;
            Outer.this.b = 2;
            d = 3;
        }
    }

    /*
    静态内部类无法访问外部类的非静态成员:
    静态内部类是存在于外部类的类中, 而不是外部类的对象中, 因此无法获取到外部类的对象 Outer.this
    */
    static class StaticInner {
        static int e;
        int f;
        static void method3() {
            Outer.a = 1;
            a = 11;
            // Outer.this.b = 2; // 'Outer.this' cannot be referenced from a static context
            e = 3;
            // f = 4; Non-static field 'f' cannot be referenced from a static context
        }
        void method4() {
            Outer.a = 1;
            a = 11;
            // Outer.this.b = 2; // 'Outer.this' cannot be referenced from a static context
            e = 3;
            f = 4;
        }
    }
}
