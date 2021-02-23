package night.reflect.internal;

import java.lang.reflect.Constructor;

/**
 *  内部实现的Class
 * @author dx_hualuo
 * @date 2020-08-06
 */
public class InternalClass {
    /**
     *  获得Class指定参数的构造器
     * @param clazz class实例
     * @param parType 构造方法参数
     * @return class构造器
     * @throws NoSuchMethodException 未找到方法异常
     */
    public static Constructor getClassConstructor(Class<?> clazz, Class<?>[] parType) throws NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor con: constructors) {
            if(equalsParTypes(con.getParameterTypes(), parType)){
                return con;
            }
        }
        throw new NoSuchMethodException();
    }

    /**
     *  比较参数类型是否兼容，如果child全部继承自或实现自或等于father，返回true
     * @param father 父参数列表
     * @param child 参数列表
     * @return 是否兼容
     */
    public static boolean equalsParTypes(Class<?>[] father, Class<?>[] child){
        if(father.length == child.length){
            for (int i = 0; i < father.length; i++) {
                if(father[i] != child[i]){
                    if (!isExtendOrImplementFrom(child[i], father[i])) {
                        Class<?> clazz;
                        if((clazz = convertToPrimitiveClass(child[i])) != null){
                            if(father[i] != clazz){
                                return false;
                            }
                        }else if((clazz = convertToPrimitiveClass(father[i])) != null){
                            if(child[i] != clazz){
                                return false;
                            }
                        }
                        else if(i == father.length - 1){
                            Class<?> componentType = father[i].getComponentType();
                            if((clazz = convertToPrimitiveClass(child[i])) != null){
                                if(componentType != clazz){
                                    return false;
                                }
                            }else if((clazz = convertToPrimitiveClass(componentType)) != null){
                                if(child[i] != clazz){
                                    return false;
                                }
                            }else if(!isExtendOrImplementFrom(child[i], componentType)){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     *  将包装类转换为基础变量类型
     * @param packagingClass 包装类Class
     * @return 基础变量类型
     */
    private static Class<?> convertToPrimitiveClass(Class<?> packagingClass){
        switch (packagingClass.getName()){
            case "java.lang.Integer": return int.class;
            case "java.lang.Double": return double.class;
            case "java.lang.Character": return char.class;
            case "java.lang.Long": return long.class;
            case "java.lang.Short": return short.class;
            case "java.lang.Byte": return byte.class;
            case "java.lang.Boolean": return boolean.class;
            case "java.lang.Float": return float.class;
            default: return null;
        }
    }

    /**
     *  判断变量是否为基本变量类型
     * @param obj obj
     * @return 是否为基本变量类型
     */
    public static boolean isBasicVariable(Object obj){
        Class<?> aClass = obj.getClass();
        return String.class == aClass || Integer.class == aClass || Double.class == aClass || Character.class == aClass || Long.class == aClass || Short.class == aClass || Byte.class == aClass || Boolean.class == aClass || Float.class == aClass;
    }

    /**
     *  检查[child]是否继承或实现自[fatherClassOrInterface]
     * @param child 需要判断的子类或子接口
     * @param fatherClassOrInterface 父类或父接口
     * @return 判断结果
     */
    public static boolean isExtendOrImplementFrom(Class<?> child, Class<?> fatherClassOrInterface){
        if(child == fatherClassOrInterface){
            return true;
        }
        return fatherClassOrInterface.isAssignableFrom(child);
    }

    /**
     *  获得参数列表对象的Class对象列表
     * @param par 参数列表
     * @return 参数类型数组
     */
    public static Class<?>[] getParametersClass(Object... par){
        Class<?>[] aClass = new Class<?>[par.length];
        for (int i = 0; i < par.length; i++) {
            if(par[i] == null){
                aClass[i] = null;
                continue;
            }
            aClass[i] = par[i].getClass();
        }
        return aClass;
    }
}
