package top.zzk0.annotation;

/*
Annotation 是代码的标记
读取时机: 编译, 类加载, 运行时
读取方法: 反射

代码阅读顺序:
BasicAnnotation             : 几个基本的注解使用
CustomAnnotation            : 利用反射来处理注解
CompileAnnotationProcessor  : 编译时处理注解的例子
*/

public class Main {
    public static void main(String[] args) {
        System.out.println("ok");
    }
}
