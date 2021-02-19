package top.zzk0.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumberTest {

    public static void main(String[] args) {
        // Math 类, 这里就展示随机数 0~1
        System.out.println(Math.random());

        // Random 类, ThreadLocalRandom 类, 用法基本一致, 后者性能更好
        Random rand = new Random();
        System.out.println(rand.nextDouble());
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom.nextDouble());

        // BigDecimal 类, 高精度计算
        BigDecimal b1 = new BigDecimal("0.05");
        BigDecimal b2 = new BigDecimal("0.01");
        BigDecimal b3 = new BigDecimal(0.05); // 不推荐
        System.out.println(b1.add(b2));
        System.out.println(b3.add(b2));

        // 大整数相加
        StringBuilder allOnes = new StringBuilder();
        StringBuilder allNines = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            allOnes.append("1");
            allNines.append("9");
        }
        BigDecimal b4 = new BigDecimal(allOnes.toString());
        BigDecimal b5 = new BigDecimal(allNines.toString());
        System.out.println(b4.add(b5));

        // 格式化输出 PI, 还有几种其他的方法: DecimalFormat, String.format
        // 使用 BigDecimal 的好处就是还可以当 double 用起来
        System.out.printf("%.4f\n", Math.PI);
        System.out.printf("%.10f\n", Math.PI);
        BigDecimal b6 = new BigDecimal("3.1415926535");
        double pi = b6.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(pi);
    }

}
