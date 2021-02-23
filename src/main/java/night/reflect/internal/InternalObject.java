package night.reflect.internal;

import night.reflect.ReflectObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *  内部实现的Object
 * @author dx_hualuo
 * @date 2020-08-06
 */
@SuppressWarnings({"UnusedReturnValue"})
public class InternalObject {
    
    /**
     *  根据类创建新实例
     * @param thisClass 类
     * @param para 构造方法参数
     * @return 实例
     */
    public static Object newInstance(Class<?> thisClass, Object... para) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        unboxParameter(para);
        Object obj;
        if(para != null){
            if(para.length == 0) {
                Constructor<?> declaredConstructor = thisClass.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                obj = declaredConstructor.newInstance();

            }
            else {
                try {
                    obj = thisClass.getDeclaredConstructor(InternalClass.getParametersClass(para)).newInstance(para);
                } catch (NoSuchMethodException e) {
                    Constructor constructor = InternalClass.getClassConstructor(thisClass, InternalClass.getParametersClass(para));
                    obj = constructor.newInstance(para);
                }
            }
        }else{
            Constructor<?> declaredConstructor = thisClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            obj = declaredConstructor.newInstance();
        }
        return obj;
    }

    /**
     *  检查参数列表是否为包装Object
     * @param obj obj
     * @return 检查结果(解包Object[])
     */
    public static Object[] unboxParameter(Object... obj){
        if(obj == null){
            return null;
        }
        for (int i = 0; i < obj.length; i++) {
            obj[i] = unboxObject(obj[i]);
        }
        return obj;
    }

    /**
     *  检查Object是否为包装Object
     * @param obj obj
     * @return 检查结果(解包Object)
     */
    public static Object unboxObject(Object obj){
        if(obj == null){
            return null;
        }
        if (obj instanceof ReflectObject){
            obj = ((ReflectObject)obj).getObject();
        }
        return obj;
    }
}
