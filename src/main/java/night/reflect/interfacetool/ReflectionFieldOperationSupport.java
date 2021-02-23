package night.reflect.interfacetool;

import night.reflect.internal.InternalField;

/**
 *  对字段反射操作的支持
 * @author dx_hualuo
 * @date 2020-08-07
 */
public interface ReflectionFieldOperationSupport {
    /**
     *  读取字段
     * @param fieldName 字段名称
     * @return 字段的值
     * @throws NoSuchFieldException 未找到字段异常
     * @throws IllegalAccessException 非法调用异常
     */
    default Object field(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return InternalField.field(getClass(),this, fieldName);
    }

    /**
     *  设置字段
     * @param fieldName 字段名字
     * @param value 字段值
     * @throws NoSuchFieldException 未找到字段异常
     * @throws IllegalAccessException 非法调用异常
     */
    default void field(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        InternalField.field(getClass(), this, fieldName, value);
    }
}
