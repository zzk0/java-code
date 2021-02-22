package top.zzk0.io;

import java.io.*;

/*
必须要实现 Serializable 接口, 否则将会抛出异常: NotSerializableException
Externalizable 接口建议加一个无参构造器, 因为需要一个对象来调用 readExternal 方法
*/


public class Person implements Serializable, Externalizable {
    private static final long serialVersionUID = 1L; // 只要序列化版本一致, 就可以进行序列化
    public static final int HANDS = 2; // 不会被序列化, 因为这个信息存储在类信息中
    public transient int age; // 指定 age 不进行序列化
    public float height;
    public String name;

    public Person() {}

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(height);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        height = in.readFloat();
        name = (String) in.readObject();
    }
}
