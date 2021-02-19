package top.zzk0.reflect;

/*
相当于 IoC 容器, 从配置文件中读取信息来初始化对象'

创建对象的方法: newInstance, 也可以用 Constructor 中的 newInstance 方法
调用方法: invoke, 第一个参数是方法调用的对象, 静态方法可以为 null
属性访问: getXxx, setXxx, Xxx 对应 8 中基本类型, 如果是引用类型, 可以直接使用 get, set
访问权限: setAccessible 可以设置 private 为可以访问的
Array: 数组的"类", 调用 newInstance(Class<?>, length) 来创建数组, set, get 可以设置获取元素
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPool {

    private Map<String, Object> pool = new HashMap<>();
    private Properties config = new Properties();

    public static void main(String[] args) {
        ObjectPool objectPool = new ObjectPool();
        objectPool.readConfig("1.txt");
        objectPool.initObject();
        objectPool.initProperty();
        System.out.println(objectPool.getObject("a"));

        try {
            Fruit fruit = (Fruit) objectPool.getObject("a");
            Field field = fruit.getClass().getDeclaredField("title");
            field.setAccessible(true);
            field.set(fruit, "ttttt");
            System.out.println(fruit);
            System.out.println(field.get(fruit));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Array 数组
        Object arr = Array.newInstance(Fruit.class, 10);
        Array.set(arr, 0, new Fruit("hhhhh"));
        Array.set(arr, 1, new Fruit("aaaaa"));
        System.out.println(Array.get(arr, 0));
        System.out.println(Array.get(arr, 1));
    }

    public void readConfig(String filename) {
        URL url = this.getClass().getClassLoader().getResource("1.txt");
        if (url == null) return;
        try(InputStream is = url.openStream()) {
            config.load(is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initObject() {
        for (String name : config.stringPropertyNames()) {
            if (!name.contains("%")) {
                pool.put(name, createObject(config.getProperty(name)));
            }
        }
    }

    public void initProperty() {
        for (String name : config.stringPropertyNames()) {
            if (name.contains("%")) {
                String[] split = name.split("%");
                String setterName = "set" + split[1].substring(0, 1).toUpperCase()
                        + split[1].substring(1);
                Class<?> clazz = getObject(split[0]).getClass();
                try {
                    Method setter = clazz.getDeclaredMethod(setterName, String.class);
                    setter.invoke(getObject(split[0]), config.getProperty(name));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getObject(String name) {
        return pool.get(name);
    }

    private Object createObject(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
