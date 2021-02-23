package night.reflect.internal;

import night.reflect.OverloadMethod;
import night.reflect.ReflectMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  内部实现的方法
 * @author dx_hualuo
 * @date 2020-08-06
 */
public class InternalMethod {
    /**
     *  用指定obj执行方法
     * @param thisClass ob的类
     * @param obj 执行所用Object
     * @param methodName 方法名
     * @param par 参数
     * @return 方法返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException 非法访问异常
     */
    public static Object method(Class<?> thisClass, Object obj, String methodName, Object... par) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InternalObject.unboxParameter(par);
        Method method = OverloadMethod.getMethod(ReflectMethod.getMethod(thisClass, methodName), InternalClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException("方法名：" + methodName);
        }
        method.setAccessible(true);
        if (ReflectMethod.isStatic(method)) {
            return method.invoke(null, par);
        }
        return method.invoke(obj, par);
    }
}
