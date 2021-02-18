package top.zzk0.annotation;

/*
元 Annotation: 用来修饰注解, 可以用来创建新的注解
@Retention  : 修饰这个注解可以保留的时间, 编译时, 加载时, 运行时. RetentionPolicy.RUNTIME 运行时
@Target     : 这个注解可以修饰的对象 ElementType.METHOD 表示只能修饰方法
@Documented : javadoc 生成文档的时候是否保留这个注解
@Inherited  : 这个注解是否可以继承, 比如修饰了父类, 子类是否也需要这个注解

这个文件定义了两个注解和一个类, Alive, Overridable, Germ 主要在于阐述使用方法.

进一步的例子: 请看 AnnotationTest, Test 这两个, 一个类似 Junit 的例子
另一个例子: JPanel 中的按钮都需要一个 Listener, 可以使用注解来帮助我们创建对象,
          请看 ActionListenerFor, Window, 仔细看 Window 中下面那个处理注解的类
*/

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class CustomAnnotation {

    public static void main(String[] args) throws Exception {
        // 基本的使用
        Annotation[] annotations = Class.forName("top.zzk0.annotation.Germ")
                .getMethod("say").getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
            if (annotation instanceof Overridable) {
                Overridable overridable = (Overridable)annotation;
                System.out.println("It is Overridable: " + overridable.value()
                        + ", message: " + overridable.msg());
            }
        }

        // 仿 Junit
        AnnotationTest annotationTest = new AnnotationTest();
        int count = 0;
        int failed = 0;
        for (Method method : annotationTest.getClass().getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(annotationTest);
                    System.out.println("TestCase" + count + ": passed");
                }
                catch (Exception e) {
                    failed++;
                    System.out.println("TestCase" + count + ": failed");
                }
                count++;
            }
        }
        System.out.println("Passed: " + (count - failed) + ", Failed: " + failed);

        // ActionListener 注解
        Window window = new Window();
        window.init();
    }
}


@Target(ElementType.TYPE)
@interface Alive {
    String name() default "zzz";
    int age() default 18;
    int height();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Overridable {
    boolean value();
    String msg() default "hello";
}

@Alive(height = 2)
class Germ {
    @Overridable(false)
    public void say() {
        System.out.println("germ");
    }
}


