package night.reflect;

import night.reflect.entity.Fields;
import night.reflect.entity.Methods;
import night.reflect.internal.InternalClass;
import night.reflect.internal.InternalObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author dx_hualuo
 */
public class ReflectObject {
    /**
     *  当前反射对象，包装的对象
     */
    Object obj;
    /**
     *  当前反射对象的Class
     */
    Class<?> thisClass;
    /**
     *  当前反射对象所包含的方法
     */
    private Methods method;
    /**
     *  当前反射对象所包含的字段
     */
    private Fields field;
    /**
     *   通过class对象和构造方法参数创建反射对象
     * @param aClass 通过这个类创建反射对象实例
     * @param par 构造方法参数
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     */
     public ReflectObject(Class<?> aClass, Object... par) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
         //引用内部实现
         obj = InternalObject.newInstance(aClass, par);
         this.thisClass = aClass;
     }

    /**
     *  通过类名创建反射实例
     * @param className 类名
     * @param par 构造方法参数
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     * @throws ClassNotFoundException 类没有找到异常
     */
    public ReflectObject(String className, Object... par) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this(ReflectClass.getClass(className), par);
    }

    /**
     *  通过包装任意object实例，来使用反射
     * @param obj 对象
     */
    public ReflectObject(Object obj){
        this.obj = obj;
        thisClass = obj.getClass();
    }

    /**
     *  新实例
     * @param tClass 类名
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 非法访问异常
     */
    public ReflectObject(Class<?> tClass) throws IllegalAccessException, InstantiationException {
        obj = tClass.newInstance();
        thisClass = tClass;
    }

    /**
     *      执行方法，不带参数
     * @param methodName 方法名
     * @return  返回执行方法的返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException 非法访问异常
     */
    public Object method(String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        checkSetMethod();
        Method method = OverloadMethod.getMethod(this.method.getMethod(methodName), InternalClass.getParametersClass());
        if(method == null){
            throw new NoSuchMethodException("方法名：" + methodName);
        }
        method.setAccessible(true);
        if (ReflectMethod.isStatic(method)) {
            return method.invoke(null);
        }
        return method.invoke(obj);
    }

    /**
     *  设置以一个class的环境去用此Object执行和取值
     * @param thisClass Class
     */
    public ReflectObject setClass(Class<?> thisClass){
        if(this.thisClass == thisClass){
            return this;
        }
        this.thisClass = thisClass;
        method = null;
        field = null;
        return this;
    }

    /**
     *  执行方法，带参数
     * @param methodName 方法名
     * @param par 参数
     * @return 方法返回值
     * @throws NoSuchMethodException 未找到方法异常
     * @throws InvocationTargetException 调用目标异常
     * @throws IllegalAccessException 非法访问异常
     */
    public Object method(String methodName, Object... par) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        checkSetMethod();
        InternalObject.unboxParameter(par);
        Method method = OverloadMethod.getMethod(this.method.getMethod(methodName), InternalClass.getParametersClass(par));
        if(method == null){
            throw new NoSuchMethodException("方法名：" + methodName);
        }
        method.setAccessible(true);
        if (ReflectMethod.isStatic(method)) {
            return method.invoke(null, par);
        }
        return method.invoke(obj, par);
    }

    /**
     *  获得反射方法
     * @param methodName 方法名
     * @return 反射方法
     * @throws NoSuchMethodException 未找到方法异常
     */
    public ReflectMethod getReflectMethod(String methodName) throws NoSuchMethodException {
        checkSetMethod();
        return new ReflectMethod(obj, method.getMethod(methodName));
    }



    /**
     *  获得该对象的所有方法
     * @return Method数组
     */
    public Method[] getSimpleMethods() {
        checkSetMethod();
        return method.getMethods();
    }

    /**
     *  返回该对象的包装方法对象
     * @return Methods
     */
    public Methods getMethods() {
        checkSetMethod();
        return method;
    }

    /**
     *  字段取值
     * @param fieldName 字段名称
     * @return 字段内容
     * @throws NoSuchFieldException 未找到字段异常
     * @throws IllegalAccessException 非法访问异常
     */
    public Object field(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        checkSetField();
        Field field = this.field.getField(fieldName);
        if(field == null){
            field = ReflectField.getField(thisClass, fieldName);
        }
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
    public void field(String fieldName, Object val) throws NoSuchFieldException, IllegalAccessException {
        checkSetField();
        val = InternalObject.unboxObject(val);
        Field field = getField(fieldName);
        field.setAccessible(true);
        field.set(obj, val);
    }

    /**
     *  获得字段
     * @param fieldName 字段名
     * @return 反射字段
     * @throws NoSuchFieldException 未找到字段异常
     */
    public ReflectField getReflectField(String fieldName) throws NoSuchFieldException {
        checkSetField();
        Field field = getField(fieldName);
        return new ReflectField(obj, field, thisClass);
    }

    /**
     *  递归查找父类字段
     * @param fieldName 字段
     * @return Field
     * @throws NoSuchFieldException 未找到字段异常
     */
    protected Field getField(String fieldName) throws NoSuchFieldException {
        Field field = null;
        try {
            field = this.field.getField(fieldName);
        }catch (NoSuchFieldException exception){
            Class<?> superClass;
            while((superClass = thisClass.getSuperclass()) != null){
                Fields fields = ReflectField.getAllFieldToFields(superClass);
                try{
                    field = fields.getField(fieldName);
                }catch (NoSuchFieldException exception1){
                    continue;
                }
                break;
            }
            if(field == null){
                throw exception;
            }
        }
        return field;
    }

    /**
     *  获得当前对象的class上的注解
     * @return 注解
     */
    public Annotation[] classAnnotations(){
        return thisClass.getAnnotations();
    }

    /**
     *  获得字段上面的注解
     * @param fieldName 字段名
     * @return 注解
     * @throws NoSuchFieldException 未找到方法异常
     */
    public Annotation[] fieldAnnotations(String fieldName) throws NoSuchFieldException {
        return getFields().getField(fieldName).getAnnotations();
    }

    /**
     *  获得方法上面的注解
     * @param methodName 方法名
     * @param paramClass 参数类型
     * @return 注解
     * @throws NoSuchMethodException 未找到方法异常
     */
    public Annotation[] methodAnnotations(String methodName, Class<?>[] paramClass) throws NoSuchMethodException {
        return getMethods().getMethod(methodName, paramClass).getAnnotations();
    }

    /**
     *  获得方法参数上面的注解
     * @param methodName 方法名
     * @param paramClass 参数类型
     * @param parameterName 参数名
     * @return 注解
     * @throws NoSuchMethodException 未找到方法异常
     */
    public Annotation[] methodParameterAnnotations(String methodName, Class<?>[] paramClass, String parameterName) throws NoSuchMethodException {
        Parameter[] params = methodParameters(methodName, paramClass);
        for (Parameter param :params) {
            if(param.getName().equals(parameterName)){
                return param.getAnnotations();
            }
        }
        return null;
    }

    /**
     *  获得方法的参数
     * @param methodName 方法名
     * @param paramClass 参数类型
     * @return 参数
     * @throws NoSuchMethodException 未找到方法异常
     */
    public Parameter[] methodParameters(String methodName, Class<?>... paramClass) throws NoSuchMethodException {
        return getMethods().getMethod(methodName, paramClass).getParameters();
    }

    /**
     *  获得包装字段
     * @return 字段
     */
    public Fields getFields() {
        checkSetField();
        return this.field;
    }

    /**
     *  获得该对象的java原生反射字段对象
     * @return Field数组
     */
    public Field[] getSimpleFields(){
        checkSetField();
        return this.field.getFields();
    }

    /**
     *  获得该对象的所有字段名
     * @return String[]
     */
    public String[] getFieldNames(){
        checkSetField();
        return this.field.getNames();
    }

    /**
     *  获得未包装的Object
     * @return 该对象所包装的Object
     */
    public Object getObject(){
        return obj;
    }

    /**
     *  获得被包装的Object的类型
     * @return Class实例
     */
    public Class<?> getObjectClass(){
        return thisClass;
    }

    /**
     * 检查方法是否被装载
     */
    private void checkSetMethod(){
        if(method == null){
            method = ReflectMethod.getAllMethodToMethods(thisClass);
        }
    }

    /**
     *  检查字段是否被装载
     */
    private void checkSetField(){
        if(field == null){
            field = ReflectField.getAllFieldToFields(thisClass);
        }
    }
}
