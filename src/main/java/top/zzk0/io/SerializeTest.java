package top.zzk0.io;

import java.io.*;

public class SerializeTest {

    public static void main(String[] args) {
        // 写入
        Person person = new Person(18, 180, "hhh");
        File file = new File("person.obj");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(person);
            oos.writeObject(person);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // 读取
        Person person1 = null;
        Person person2 = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            person1 = (Person) ois.readObject();
            person2 = (Person) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取信息, 判断是否相等
        System.out.println(person1); // age = 0, int 的默认值
        System.out.println("两个对象是否相等: " + (person == person1)); // no
        System.out.println("person1 == person2? " + (person1 == person2)); // yes

        // 使用 Externalizable 接口, 写入
        File file1 = new File("person1.obj");
        try (FileOutputStream fos = new FileOutputStream(file1);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            person1.writeExternal(oos);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // 读出
        Person person3 = new Person();
        try (FileInputStream fis = new FileInputStream(file1);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            person3.readExternal(ois);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(person3);
    }
}
