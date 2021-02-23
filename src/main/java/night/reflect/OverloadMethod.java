package night.reflect;

import night.reflect.internal.InternalClass;

import java.lang.reflect.Method;

/**
 *  重载方法
 * @author dx_hualuo
 */
public class OverloadMethod {
    /**
     *  从方法数组中获得与参数列表兼容的方法
     * @param methods 方法数组
     * @param parameter 参数列表
     * @return Method对象
     */
    public static Method getMethod(Method[] methods, Class<?>[] parameter) {
        for (Method method: methods) {
            if(InternalClass.equalsParTypes(method.getParameterTypes(), parameter)){
                return method;
            }
        }
        return null;
    }
    private final Method[] methods;
    public OverloadMethod(Method[] method) {
        methods = method;
        safetyCheck();
    }

    public OverloadMethod(Method method){
        methods = new Method[1];
        methods[0] = method;
    }

    public OverloadMethod(Method[] method, boolean isSafety) {
        methods = method;
        if (!isSafety) {
            safetyCheck();
        }
    }



    public Method getMethod(Class<?>[] parameter){
        for (Method method: methods) {
            if(InternalClass.equalsParTypes(method.getParameterTypes(), parameter)){
                return method;
            }
        }
        return null;
    }

    public int count(){
        return methods.length;
    }

    public Method get(int index){
        try {
            return methods[index];
        } catch (Exception e) {
            return null;
        }
    }

    public String getName(){
        if(methods != null && methods.length > 0){
            return methods[0].getName();
        }
        return null;
    }

    Method[] getMethods(){
        return methods;
    }
    private void safetyCheck(){
        if (methods == null || methods.length == 0) {
            throw new NullPointerException();
        }
        String name = methods[0].getName();
        for (Method method : methods) {
            if (!method.getName().equals(name)) {
                throw new RuntimeException("不是同名重载方法！");
            }
        }
    }
}
