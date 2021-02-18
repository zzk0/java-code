package top.zzk0.annotation;

/* 基本 Annotation

@Override           : 一定要覆盖父类的方法, 不然编译会有提示
@Deprecated         : 过期方法
@SuppressWarning    : 抑制警告
@SafeVarargs        : 安全可变参数, 泛型加可变参数会引起"堆污染"警告

堆污染:
List list = new ArrayList<Integer>();
List<String> temp = list;
temp.add("asdf"); // 所谓的"堆污染"
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicAnnotation {

    public static void main(String[] args) {
        // @Override
        Dog dog = new Dog();
        dog.sax();

        // @Deprecated, 可以调用, 编译运行无警告
        Fish fish = new Fish();
        fish.say();

        // @SuppressWarning, 无警告
        Human human = new Human();
        human.say();

        // @SafeVarargs, 无警告, emm, 怎么都没警告呢?
        Alien alien = new Alien();
        alien.say(Arrays.asList("hhh"), Arrays.asList("aaa"));
    }
}

// ---------------------------------- [start] @Override ----------------------------------

class Animal {
     public void say() {
         System.out.println("I'm alive");
     }
}

class Dog extends Animal {
    @Override
    public void say() {
        System.out.println("Wooooo...");
    }

    // @Override: java: 方法不会覆盖或实现超类型的方法, 编译失败
    public void sax() {
        System.out.println("wrong spell");
    }
}

// ---------------------------------- [end] @Override ----------------------------------

// ---------------------------------- [start] @Deprecated ----------------------------------

class Fish extends Animal {
    @Deprecated
    public void say() {
        System.out.println("I cannot say anything");
    }
}

// ---------------------------------- [end] @Deprecated ----------------------------------

// ---------------------------------- [start] @SuppressWarning ----------------------------------

class Human extends Animal {
    // @SuppressWarnings(value = "unchecked"), 似乎用和不用没区别也
    public void say() {
        List<Integer> list = new ArrayList();
        list.add(10);
        System.out.println("human want to talk");
    }
}

// ---------------------------------- [end] @SuppressWarning ----------------------------------

// ---------------------------------- [start] @SafeVarargs ----------------------------------

class Alien {
    public void say(List<String>... listStr) {
        List[] listArray = listStr;
        System.out.println("zxnlhk;asdfjlk;h");
    }
}

// ---------------------------------- [end] @SafeVarargs ----------------------------------

