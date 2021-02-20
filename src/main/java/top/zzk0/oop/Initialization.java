package top.zzk0.oop;

/*
初始化块顺序:

静态代码块优先, 如果有父类, 递归运行父类静态代码块
接着初始化代码块 -> 构造器

打印内容:
GrandFather 静态初始化代码块
Father 静态初始化代码块
Son 静态初始化代码块
GrandFather 初始化代码块
GrandFather 构造器
Father 初始化代码块
Father 构造器
Son 初始化代码块
Son 初始化代码块2, 按定义顺序执行
Son 构造器, a = 22222
----------------------------
GrandFather 初始化代码块
GrandFather 构造器
Father 初始化代码块
Father 构造器
Son 初始化代码块
Son 初始化代码块2, 按定义顺序执行
Son 构造器, a = 22222
*/

public class Initialization {

    public static void main(String[] args) {
        new Son();
        System.out.println("----------------------------");
        new Son();
    }

}

class GrandFather {
    {
        System.out.println(GrandFather.class.getSimpleName() + " 初始化代码块");
    }
    static {
        System.out.println(GrandFather.class.getSimpleName() + " 静态初始化代码块");
    }
    public GrandFather() {
        System.out.println(GrandFather.class.getSimpleName() + " 构造器");
    }
}

class Father extends GrandFather {
    {
        System.out.println(Father.class.getSimpleName() + " 初始化代码块");
    }
    static {
        System.out.println(Father.class.getSimpleName() + " 静态初始化代码块");
    }
    public Father() {
        System.out.println(Father.class.getSimpleName() + " 构造器");
    }
}

class Son extends Father {
    {
        System.out.println(Son.class.getSimpleName() + " 初始化代码块");
        a = 20;

        // 还可以声明别的变量
        int x = 2333;
    }
    {
        System.out.println(Son.class.getSimpleName() + " 初始化代码块2, 按定义顺序执行");
    }
    static {
        System.out.println(Son.class.getSimpleName() + " 静态初始化代码块");
    }

    int a = 10;

    {
        a = 22222;
    }

    public Son() {
        System.out.println(Son.class.getSimpleName() + " 构造器, a = " + a);
    }
}
