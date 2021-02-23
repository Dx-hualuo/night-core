package night.data.util;

import night.reflect.ReflectObject;

import java.util.Arrays;
import java.util.List;

/**
 *  方便重写ToString的工具类
 *   * 警告 * 此工具类属于低运行效率工具类
 *           此工具对循环引用未经处理
 * @author dx_hualuo
 */
public class ToString {
    /**
     *  声明十六进制的哈希值对齐长度
     */
    public final static int HASHCODE_LENGTH = 8;

    /**
     *  将一个对象ToString
     * @param obj 对象
     * @return 字符串
     */
    public static String object(Object obj){
        StringBuilder hashCode = new StringBuilder(Integer.toHexString(obj.hashCode()));
        while (hashCode.length() < HASHCODE_LENGTH) {
            hashCode.append(" ");
        }
        ReflectObject object = new ReflectObject(obj);
        StringBuilder result = new StringBuilder("[");
        result.append(hashCode).append("] >>> ");
        result.append(object.getObjectClass().getSimpleName()).append("{this:{");
        result.append(field(object));
        result.append("}");
        Class<?> superClass;
        while((superClass = object.getObjectClass().getSuperclass()) != Object.class){
            object.setClass(superClass);
            result.append(" ,").append(superClass.getSimpleName()).append("{");
            result.append(field(object)).append("}");
        }
        result.append("}");
        return result.toString();
    }

    public static String object(List<?> list){
        return list.toString();
    }

    /**
     *  将一个对象的字段ToString
     * @param obj 对象
     * @return 字符串
     */
    public static String field(Object obj){
        return field(new ReflectObject(obj));
    }

    /**
     *  内部使用的字段ToString
     * @param object 对象
     * @return 字符串
     */
    private static String field(ReflectObject object){
        StringBuilder result = new StringBuilder();
        String[] fieldNames = object.getFieldNames();
        for (String fieldName : fieldNames) {
            Object field ;
            try {
                field = object.field(fieldName);
            } catch (Exception e) {
                throw new RuntimeException("ToString失败！" + e.getLocalizedMessage());
            }
            result.append(fieldName).append("=").append(field).append(" ,");
        }
        if (fieldNames.length > 0){
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    /**
     *  对数组输出转String
     * @param array 数组
     * @param <T> 数组类型
     * @return ToString字符串
     */
    public static <T> String array(T[] array){
        return Arrays.toString(array);
    }
}
