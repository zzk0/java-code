package top.zzk0.reflect;

/*
动态代理有两种使用方法:
getProxyClass, 在使用的时候, 需要去获取构造器, 然后 newInstance 的时候还需要传入 handler
newProxyInstance, 直接生成对象

注意: 运行这个代码的时候, 如果用 IDEA 的调试模式, 还会调用 toString 方法, 显示当前变量
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopTest {

    public static void main(String[] args) throws Exception {
        // 正常调用
        Animal animal0 = new Dog();
        animal0.walk();
        animal0.echo("dog god");

        // getProxyClass 例子: 使用动态代理, 直接代理接口!
        Class<?> clazz0 = Proxy.getProxyClass(AopTest.class.getClassLoader(), Animal.class);
        Constructor<?> ctor = clazz0.getConstructor(InvocationHandler.class);
        Animal animal1 = (Animal)ctor.newInstance(new Object[]{new InterfaceInvocationHandler()});
        animal1.walk();
        animal1.echo("interface");

        // newProxyInstance 例子: 使用动态代理, 直接代理接口
        Animal animal2 = (Animal)Proxy.newProxyInstance(AopTest.class.getClassLoader(),
                new Class[]{Animal.class}, new InterfaceInvocationHandler());
        animal2.walk();
        animal2.echo("wwwww");

        // newProxyInstance 例子: 使用动态代理, 直接代理 animal0
        Dog dog = new Dog();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.setProxy(dog);
        Animal animal3 = (Animal)Proxy.newProxyInstance(dog.getClass().getClassLoader(),
                dog.getClass().getInterfaces(), myInvocationHandler);
        animal3.walk();
        animal3.echo("wwwww");
    }
}

class InterfaceInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("正在执行的方法: " + method);
        return null;
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public void setProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        AnimalUtil.method1();
        Object result = method.invoke(target, args);
        AnimalUtil.method2();
        return result;
    }
}
