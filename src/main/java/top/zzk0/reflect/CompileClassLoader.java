package top.zzk0.reflect;

/*
这个类的作用展示了一个 ClassLoader, 覆盖了 findClass 方法来找到 Class<?>
.java -> 编译成 .class -> 读取到 byte[] 数组 -> defineClass 将二进制转为 Class 对象 -> 返回

先编译这个类
> javac -encoding utf-8 .\top\zzk0\reflect\CompileClassLoader.java

然后我们调用这个类去加载 ClassLoaderTest
> java top.zzk0.reflect.CompileClassLoader top.zzk0.reflect.ClassLoaderTest

这种自定义的 ClassLoader 还可以完成的事情:
验证数字签名, 看看这个类是否未经过修改, 是否安全
解密"经过加密的"类文件
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class CompileClassLoader extends ClassLoader {

    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        try(FileInputStream fin = new FileInputStream(file)) {
            int r = fin.read(raw);
            if ((r != len)) {
                throw new IOException("无法读取全部内容");
            }
            return raw;
        }
    }

    private boolean compile(String javaFile) throws IOException {
        System.out.println("正在编译文件: " + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac -encoding utf-8 " + javaFile);
        try {
            p.waitFor();
        }
        catch (InterruptedException e) {
            System.out.println("被中断了");
        }
        int ret = p.exitValue();
        return ret == 0;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);

        if (javaFile.exists() && !classFile.exists() || javaFile.lastModified() > classFile.lastModified()) {
            try {
                if (!compile(javaFilename) || !classFile.exists()) {
                    throw new ClassNotFoundException("没有找到类");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (classFile.exists()) {
            try {
                byte[] raw = getBytes(classFilename);
                clazz = defineClass(name, raw, 0, raw.length);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }

        return clazz;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("缺少目标类");
        }
        String progClass = args[0];
        String[] progArgs = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);
        CompileClassLoader compileClassLoader = new CompileClassLoader();
        Class<?> clazz = compileClassLoader.loadClass(progClass);

        Method method = clazz.getDeclaredMethod("main", (new String[0]).getClass());
        Object[] argsArray = {progArgs};
        method.invoke(null, argsArray);
    }
}
