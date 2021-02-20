package top.zzk0.generic;

/*
代码阅读顺序:
GenericBasic        : 泛型基本的使用方法, 泛型类和继承, 通配符, 上限
GenericMethod       : 泛型方法, 泛型构造器, 下限, 类型擦除, 泛型数组
GenericReturnValue  : 泛型返回值, 强制转化

泛型需要掌握几个内容:
泛型的使用: 泛型类, 泛型方法, 泛型构造器, 泛型类的子类(是否泛型取决于子类)
通配符 ? 的使用
extends 上限, super 下限, 方法重载会冲突
擦除, T 其实并不存在, 不能: static T xxx, new T[10], static void method(T t), instanceof List<Integer>
泛型数组为什么的正确用法, new List<String>[10] 为什么不行

设计原则: 编译时不出现 unchecked 警告, 就不会有 ClassCastException 异常
*/

import java.util.ArrayList;
import java.util.List;

public class Main {

}
