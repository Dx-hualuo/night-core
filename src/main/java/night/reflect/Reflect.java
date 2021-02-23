package night.reflect;

import night.reflect.exception.NotArrayTypeException;
import night.reflect.internal.InternalClass;
import night.reflect.internal.InternalObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  反射包下整合的可以对外开放的工具方法
 * @author dx_hualuo
 * @date 2020-08-07
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class Reflect {
    /**
     *  通过指定类型和参数调用构造方法创建实例
     * @param clazz 指定类型
     * @param par 构造方法参数，可以为空
     * @return 新实例Object
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 方法未找到异常
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     */
    public static Object newInstance(Class<?> clazz, Object... par) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return InternalObject.newInstance(clazz, par);
    }
    /**
     *  新实例
     * @param className 类名
     * @param par 参数名
     * @return ReflectObject实例
     * @throws ClassNotFoundException 类未找到
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 方法未找到异常
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     */
    public static Object newInstance(String className, Object... par) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return newInstance(ReflectClass.getClass(className), par);
    }


    public static <T> T[] newArray(Class<?> clazz, int... length){
        if(length.length == 1){
            return (T[])new ReflectArray(clazz, length[0]).getObject();
        }
        return (T[])new ReflectArray(clazz, length).getObject();
    }

    public static Object newArray(String className, int... length) throws ClassNotFoundException {
        return newArray(ReflectClass.getClass(className), length);
    }

    /**
     *  将类型转换到反射数组
     * @param object 反射数组
     * @return ReflectArray实例
     */
    public static ReflectArray convertToReflectArray(ReflectObject object){
        if(object.getClass() == ReflectArray.class){
            return (ReflectArray)object;
        }
        else if(object.thisClass.isArray()){
            return new ReflectArray(object);
        }
        else{
            throw new NotArrayTypeException(object.thisClass);
        }
    }
    /**
     *  获得指定类下的所有方法
     * @param classType 类名
     * @return Method数组
     */
    public static Method[] getMethods(Class<?> classType){
        return ReflectMethod.getMethods(classType);
    }

    /**
     *  获得指定类名的类下的所有方法
     * @param className 类名
     * @return Method数组
     * @throws ClassNotFoundException class未找到异常
     */
    public static Method[] getMethods(String className) throws ClassNotFoundException {
        return getMethods(ReflectClass.getClass(className));
    }

    /**
     *  判断是否是基本变量类型
     * @param obj 判断对象
     * @return 是否是基本变量类型
     */
    public static boolean isBasicVariable(Object obj) {
        return InternalClass.isBasicVariable(obj);
    }


    /**
     *  判断此类中的Class是否继承或实现自fatherClassOrInterface
     * @param fatherClassOrInterface 父类或者父接口
     * @return 是否是继承关系
     */
    public static boolean isExtendOrImplementFrom(Class<?> fatherClassOrInterface, Class<?> thisClass){
        return InternalClass.isExtendOrImplementFrom(fatherClassOrInterface, thisClass);
    }
}
