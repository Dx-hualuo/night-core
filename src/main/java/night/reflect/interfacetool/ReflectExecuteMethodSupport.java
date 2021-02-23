package night.reflect.interfacetool;

import night.reflect.internal.InternalMethod;

import java.lang.reflect.InvocationTargetException;

/**
 *  方法反射执行的支持
 * @author dx_hualuo
 * @date 2020-08-07
 */
@SuppressWarnings({"unchecked"})
public interface ReflectExecuteMethodSupport {
    /**
     *  反射方式执行当前对象方法, 并返回指定类型的返回值
     * @param methodName 方法名
     * @param returnType 返回值类型
     * @param parameter 参数
     * @param <T> 返回值类型
     * @return 方法返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws IllegalAccessException 非法访问异常
     * @throws InvocationTargetException 调用目标异常
     */
    default <T> T method(String methodName, Class<T> returnType, Object... parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (T) InternalMethod.method(getClass(),this, methodName, parameter);
    }

    /**
     *  反射方式执行当前对象方法, 并返回返回值
     * @param methodName 方法名
     * @param parameter 参数
     * @return 方法返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws IllegalAccessException 非法访问异常
     * @throws InvocationTargetException 调用目标异常
     */
    default Object method(String methodName, Object... parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return InternalMethod.method(getClass(),this, methodName, parameter);
    }

    default String[] getMethodNames(){
        return null;
    }
}
