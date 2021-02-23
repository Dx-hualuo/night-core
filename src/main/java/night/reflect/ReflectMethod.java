package night.reflect;

import night.reflect.entity.Methods;
import night.reflect.internal.InternalClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * @author dx_hualuo
 */
public class ReflectMethod {
    //静态区域=======================================================================================================================
    static {
        methods = new HashMap<>();
    }
    private static final HashMap<String, Methods> methods;

    public static Method[] getMethod(Class<?> aClass, String methodName) throws NoSuchMethodException {
        String className = aClass.getName();
        if (methods.containsKey(className)) {
            Methods _methods = ReflectMethod.methods.get(className);
            Method[] oneMethod;
            if ((oneMethod = _methods.getMethod(methodName)) != null) {
                return oneMethod;
            } else {
                Method[] result = aClass.getDeclaredMethods();
                _methods.addMethod(result);
                return _methods.getMethod(methodName);
            }
        } else {
            Method[] method = aClass.getDeclaredMethods();
            Methods methods = new Methods(method);
            ReflectMethod.methods.put(className, methods);
            return methods.getMethod(methodName);
        }
    }

    public static Method[] getMethod(String className, String methodName) throws ClassNotFoundException, NoSuchMethodException {
        return getMethod(ReflectClass.getClass(className), methodName);
    }

    public static Method[] getMethods(Class<?> aClass) {
        String className = aClass.getName();
        if (methods.containsKey(className)) {
            Methods methods = ReflectMethod.methods.get(className);
            if (methods.isComplete()) {
                return methods.getMethods();
            } else {
                Method[] result = aClass.getDeclaredMethods();
                methods.addMethod(result);
                methods.setComplete();
                return result;
            }
        } else {
            Method[] method = aClass.getDeclaredMethods();
            Methods methods = new Methods(method, true);
            ReflectMethod.methods.put(className, methods);
            return method;
        }
    }

    public static Method[] getSimpleMethods(String className) throws ClassNotFoundException {
        return getMethods(ReflectClass.getClass(className));
    }
    public static Methods getAllMethodToMethods(String className) throws ClassNotFoundException {
        return getAllMethodToMethods(ReflectClass.getClass(className));
    }
    public static Methods getAllMethodToMethods(Class<?> aClass){
        String className = aClass.getName();
        if (methods.containsKey(className)) {
            Methods methods1 = ReflectMethod.methods.get(className);
            if (methods1.getMethods() != null && !methods1.isComplete()) {
                return methods1;
            } else {
                Method[] result = aClass.getDeclaredMethods();
                methods1.addMethod(result);
                methods1.setComplete();
                return methods1;
            }
        } else {
            Method[] method = aClass.getDeclaredMethods();
            Methods methods = new Methods(method);
            methods.setComplete();
            ReflectMethod.methods.put(className, methods);
            return methods;
        }
    }

    //非静态区域==========================================================================================================================
    private Object obj;
    private final OverloadMethod thisMethod;
    private final boolean isStatic;

    public ReflectMethod(Object obj, Method[] method) {
        this.obj = obj;
        this.thisMethod = new OverloadMethod(method);
        this.isStatic = isStatic(method[0]);
    }

    public ReflectMethod(Method[] method) {
        this.thisMethod = new OverloadMethod(method);
        this.isStatic = isStatic(method[0]);
    }

    public ReflectMethod(Class<?> aClass, Method method) {
        this.thisMethod = new OverloadMethod(method);
        this.isStatic = isStatic(method);
    }

    public Object invoke(Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(InternalClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(this.obj, par);
    }

    public Object invokeUseObject(Object obj, Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(InternalClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(obj, par);
    }
    public Object invokeUseObject(ReflectObject obj, Object... par) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = thisMethod.getMethod(InternalClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException();
        }
        method.setAccessible(true);
        if (this.isStatic) {
            return method.invoke(null, par);
        }
        return method.invoke(obj.getObject(), par);
    }

    public Method[] toMethods() {
        return this.thisMethod.getMethods();
    }

    public String getName(){
        return thisMethod.getName();
    }

    public static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }
}


