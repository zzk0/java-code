package top.zzk0.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GenericType {

    private Map<String, Integer> hash = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 执行这个会出错 ClassCastException, 而且还需要强制转化
        // Dog dog = (Dog)ObjectFactory.createObject0(Apple.class);

        // 执行这个就很安全了, 第一行编译失败
//        Dog dog = ObjectFactory.createObject(Apple.class);
        Dog dog = ObjectFactory.createObject(Dog.class);
        dog.walk();

        // 获取泛型的参数
        Class<GenericType> clazz = GenericType.class;
        Field f = clazz.getDeclaredField("hash");
        Class<?> a = f.getType();
        System.out.println(a); // Map
        Type gType = f.getGenericType();
        if (gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)gType;
            Type rType = pType.getRawType();
            System.out.println(rType); // Map

            Type[] tArgs = pType.getActualTypeArguments();
            for (Type tArg : tArgs) {
                System.out.println(tArg); // String, Integer
            }
        }
    }
}

class ObjectFactory {
    public static Object createObject0(Class<?> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T createObject(Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
