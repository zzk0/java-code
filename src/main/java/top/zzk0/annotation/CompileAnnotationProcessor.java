package top.zzk0.annotation;

/*
编译时处理注解的例子: 需要我们自己编写一个 AbstractProcessor 的继承类

一定要注意 @SupportedAnnotationTypes 注解里面的包名要填, 否则将找不到那些注解, process 方法就不会被调用了

执行命令:
请先到 src/main/java 文件夹
javac -encoding utf-8 .\top\zzk0\annotation\CompileAnnotationProcessor.java
.\top\zzk0\annotation\Thing.java .\top\z zk0\annotation\Creatable.java

然后,
javac -processor top.zzk0.annotation.CompileAnnotationProcessor .\top\zzk0\annotation\World.java

*/

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"top.zzk0.annotation.Creatable", "top.zzk0.annotation.Thing"})
public class CompileAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("start processing");
        PrintStream ps = null;
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(Creatable.class)) {
                Name className = element.getSimpleName();
                System.out.println("Processing " + className);
                Creatable creatable = element.getAnnotation(Creatable.class);
                ps = new PrintStream(new FileOutputStream(className + ".txt"));
                ps.println(className + "的注解成员有: ");

                for (Element field : element.getEnclosedElements()) {
                    if (field.getKind() == ElementKind.FIELD) {
                        Thing thing = field.getAnnotation(Thing.class);
                        System.out.println("Processing " + field.getSimpleName());
                        if (thing != null) {
                            ps.println(field.getSimpleName());
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
