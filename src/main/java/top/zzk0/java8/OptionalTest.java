package top.zzk0.java8;

/*
Optional 类
*/

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = null;

        Optional<Integer> ref = Optional.of(a);
        Optional<Integer> ref1 = Optional.ofNullable(b);
        Optional<Integer> ref2 = Optional.empty();

        if (ref.get() == 1) System.out.println("asdf");
        if (!ref1.isPresent()) System.out.println("null1");
        if (!ref2.isPresent()) System.out.println("null2");
    }
}
