package night.reflect;

import night.reflect.internal.InternalClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author dx_hualuo
 */
public class ReflectClass {
    private static final HashMap<String, Class> map;
    static {
        map = new HashMap<>();
        map.put("int", int.class);
        map.put("double", double.class);
        map.put("float", float.class);
        map.put("boolean", boolean.class);
        map.put("long", long.class);
        map.put("short", short.class);
        map.put("byte", byte.class);
        map.put("char", char.class);
    }

    /**
     *  通过类名获得Class实例
     * @param className 类名
     * @return Class实例
     * @throws ClassNotFoundException 类未找到异常
     */
    public static Class getClass(String className) throws ClassNotFoundException {
        Class result = map.get(className);
        if(result == null){
            result = Class.forName(className);
            map.put(className,result);
        }
        return result;
    }

    //实例方法============================================================================================================

    private final Class<?> thisClass;

    /**
     *  通过class对象来创建ReflectClass实例
     * @param thisClass class实例
     */
    public ReflectClass(Class<?> thisClass){
        this.thisClass = thisClass;
    }

    /**
     *  通过类名来创建ReflectClass实例
     * @param className 类名
     */
    public ReflectClass(String className) throws ClassNotFoundException {
        this.thisClass = ReflectClass.getClass(className);
    }

    /**
     *  获得类上面的注解
     * @param annotationType 注解类型
     * @param <T> 注解类型
     * @return 注解
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationType){
        return this.thisClass.getAnnotation(annotationType);
    }

    /**
     *  静态方法
     * @param methodName 方法
     * @return 方法返回值
     * @throws NoSuchMethodException 未找到方法异常
     */
    public ReflectMethod staticMethod(String methodName) throws NoSuchMethodException {
        Method[] method = ReflectMethod.getMethod(thisClass, methodName);
        if(method == null || method.length == 0){
            throw new NoSuchMethodException();
        }
        if (ReflectMethod.isStatic(method[0])) {
            return new ReflectMethod(thisClass, method);
        }
        throw new NoSuchMethodException();
    }

    public Object staticMethod(String methodName, Object... par) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] methods = ReflectMethod.getMethod(thisClass,methodName);
        OverloadMethod me = new OverloadMethod(methods);
        Method method = me.getMethod(InternalClass.getParametersClass(par));
        if (ReflectMethod.isStatic(method)) {
            return method.invoke(null, par);
        }
        throw new NoSuchMethodException();
    }

    public Object staticField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field fl = ReflectField.getField(thisClass, fieldName);
        if (ReflectField.isStatic(fl)) {
            return fl.get(thisClass);
        }else{
            throw new NoSuchFieldException();
        }
    }

    public Object staticField(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        Field fl = ReflectField.getField(thisClass, fieldName);
        if (ReflectField.isStatic(fl)) {
            fl.set(thisClass, val);
        }else{
            throw new NoSuchFieldException();
        }
        return val;
    }

    /**
     *  获得被包装的Class
     * @return Class实例
     */
    public Class<?> toClass(){
        return thisClass;
    }
}
