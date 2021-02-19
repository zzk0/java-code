package top.zzk0.reflect;

/*
Class 有三种获取方法:
Class.forName
XXX.class
Object 下面的 getClass() 方法

使用 Class 类, 我们可以获取类的信息
Constructor, Method, Field, Annotation, 内部类, 外部类, Modifier, 包名类名
*/

import java.lang.annotation.Annotation;

public class ClassInfo {

    public static void main(String[] args) {
        Class<?> appleClass = Apple.class;
        Annotation[] annotations0 = appleClass.getAnnotations();
        Annotation[] annotations1 = appleClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations0) {
            System.out.println(annotation);
        }
        System.out.println("-------------------");
        for (Annotation annotation : annotations1) {
            System.out.println(annotation);
        }
    }
}
