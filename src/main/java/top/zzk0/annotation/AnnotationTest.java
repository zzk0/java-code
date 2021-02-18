package top.zzk0.annotation;

import java.util.List;

public class AnnotationTest {

    @Test
    public void m1() {
        int a = 10;
    }

    @Test
    public void m2() {
        int[] arr = new int[10];
        arr[10] = 7;
    }

    @Test
    public void m3() {
        List<Integer> list = null;
        list.add(10);
    }

    public void m4() {
        System.out.println("Don't test this");
    }
}
