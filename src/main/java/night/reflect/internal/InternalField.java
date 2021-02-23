package night.reflect.internal;

import night.reflect.ReflectField;

import java.lang.reflect.Field;

/**
 *  内部实现的Field
 * @author dx_hualuo
 */
public class InternalField {
    /**
     *  字段取值
     * @param fieldName 字段名称
     * @return 字段内容
     * @throws NoSuchFieldException 未找到字段异常
     * @throws IllegalAccessException 非法访问异常
     */
    public static Object field(Class<?> thisClass, Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = ReflectField.getField(thisClass, fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     *  字段赋值
     * @param fieldName 字段名称
     * @param val 值
     * @throws NoSuchFieldException 未找到字段异常
     * @throws IllegalAccessException 非法访问异常
     */
    public static void field(Class<?> thisClass, Object obj, String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        Field field = ReflectField.getField(thisClass, fieldName);
        if(field == null){
            throw new NoSuchFieldException(fieldName+"未找到！");
        }
        field.setAccessible(true);
        field.set(obj, val);
    }
}
