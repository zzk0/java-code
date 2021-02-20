package top.zzk0.oop;

/*
总结: 枚举类和普通类其实没有本质区别, 对比起来, enum 简化开发, 比如修饰了成员(public static final)

枚举类构造器只能是 private 的, 默认就是 private
枚举类的实例只能在第一行列出, 默认都是 public static final 修饰
非抽象的枚举类, 默认使用 final 修饰, 不能派生子类
枚举类可以实现接口, 也可以有抽象方法(自动在 enum 关键字前加 abstract), 这些枚举类是抽象的
枚举类可以使用 switch 语句
enum 自动继承自 java.lang.Enum

这个代码文件主要包括几个:
1. 用传统的方式实现一个枚举类
2. 枚举类的使用方法
3. 实现接口的枚举类
4. 带抽象方法的枚举类

对于 3, 4 中, 枚举类的实例可以利用类似匿名内部类的方式去初始化
*/

public class EnumTest {

    public static void main(String[] args) {
        // 例子1: 自定义枚举类
        Season s1 = Season.SPRING;

        // 例子2: 普普通通枚举类
        Week week = Week.WEEKDAY;

        // 例子3: 接口枚举类
        Gender gender = Gender.MALE;
        gender.show();

        // 例子4: 抽象枚举类
        Book book = Book.SCIENCE;
        book.show();
    }
}

class Season {
    int number;
    String name;

    public static final Season SPRING = new Season(1, "春天");
    public static final Season SUMMER = new Season(2, "夏天");
    public static final Season AUTUMN = new Season(3, "秋天");
    public static final Season WINTER = new Season(4, "冬天");

    public Season(int number, String name) {
        this.number = number;
        this.name = name;
    }
}

enum Week {
    WEEKDAY("工作日"),
    WEEKEND("周末");

    String type;
    Week(String type) {
        this.type = type;
    }
}

interface Showable {
    void show();
}

enum Gender implements Showable {
    FEMALE, MALE;

    @Override
    public void show() {
        switch (this) {
            case FEMALE:
                System.out.println("女性");
            case MALE:
                System.out.println("男性");
        }
    }
}

enum Book {
    SCIENCE() {
        @Override
        void show() {
            System.out.println("Science");
        }
    },
    CHILDREN() {
        @Override
        void show() {
            System.out.println("Children");
        }
    },
    COMIC() {
        @Override
        void show() {
            System.out.println("Comic");
        }
    };

    abstract void show();
}
