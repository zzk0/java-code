package top.zzk0.util;

/*
String StringBuffer StringBuilder(和 StringBuffer 基本相同, 不过 Builder 线程不安全, 单线程优选)
使用 StringBuffer/StringBuilder 的原因是, String 的不变性
String 每次 + 都会产生一个新的引用, 所以会生成很多对象

比如:
String str = "asdf";
str = str + "x";
str = str + "y";

上面会产生三个字符串, 所以要用 StringBuffer

需要注意到 char 是 16bit 的
String 的构造函数有传入 byte[] 类型的, 传入这个类型还需要选择字符集, 否则不知道怎么转为字符
*/

public class StringTest {

    public static void main(String[] args) {
        // char 16bit 范围从 \u0000 ~ \uffff
        char x = '中';
        System.out.println((int)x);
        x = '\u9876';
        System.out.println(x);

        // 对象相加, 这个就是 Java 的语法糖, 编译的时候, 会搞成 StringBuilder
        // Java 并不支持重载运算符
        String str = new String("asd") + new String("dfg");
        System.out.println(str);
    }
}
