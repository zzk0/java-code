package top.zzk0.reflect;

public class Dog implements Animal {

    @Override
    public void walk() {
        System.out.println("walk");
    }

    @Override
    public void echo(String message) {
        System.out.println(message);
    }
}
