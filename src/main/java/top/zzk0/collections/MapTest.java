package top.zzk0.collections;

/*
Map 的实现类比较多
HashMap         : 散列表
LinkedHashMap   : 维护插入顺序的散列表, 继承了 HashMap
Hashtable       : 古老版本散列表, 命名不规范2333, 线程安全
Properties      : 用来读取保存属性, 非常方便, 继承了 Hashtable
SortedMap       : 接口, 提供自然排序, 定制排序
TreeMap         : 红黑树, 实现了 SortedMap 接口
IdentityHashMap : 类似 System.identityHashCode(), 根据引用地址来做散列, 用 == 做判断
WeakHashMap     : key 弱引用, 可能被 gc, value 强引用也不管用, 看下面代码
EnumMap         : 专门为枚举类设计, key 是枚举类, 自然排序
*/

import java.util.SortedMap;
import java.util.WeakHashMap;

public class MapTest {

    public static void main(String[] args) throws Exception {
        WeakHashMap<String, String> hashMap = new WeakHashMap<>();
        hashMap.put("asdf", new String("zxcv"));
        hashMap.put("qwe", "sdfg");
        hashMap.put(new String("lkj"), "yuio");
        hashMap.put(new String("iuyt"), new String("kjhg"));
        System.out.println(hashMap);
        System.gc();
        Thread.sleep(2000);
        System.out.println(hashMap);
    }
}
