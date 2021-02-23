package night.reflect;

import night.reflect.entity.Fields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ReflectField {
    //静态区域=======================================================================================================================
    /**
     *  存储各类所拥有的字段
     */
    private static final HashMap<String, Fields> FIELDS_HASH_MAP;

    static {
        FIELDS_HASH_MAP = new HashMap<>();
    }

    /**
     *  获得Class拥有指定名字的字段
     * @param _class class对象
     * @param fieldName 字段名字
     * @return 字段实例
     * @throws NoSuchFieldException 未找到字段异常
     */
    public static Field getField(Class<?> _class, String fieldName) throws NoSuchFieldException {
        String className = _class.getName();
        if(FIELDS_HASH_MAP.containsKey(className)){
            Fields fields = FIELDS_HASH_MAP.get(className);
            Field oneMethod;
            if((oneMethod = fields.getField(fieldName)) != null){
                return oneMethod;
            }
            else{
                Field result = _class.getDeclaredField(fieldName);
                fields.addField(result);
                return result;
            }
        }else{
            Field field = _class.getDeclaredField(fieldName);
            Fields fields = new Fields(field);
            FIELDS_HASH_MAP.put(className, fields);
            return field;
        }
    }

    /**
     *  通过类名获得指定名字的字段
     * @param className 类名
     * @param fieldName 字段名
     * @return 字段
     * @throws ClassNotFoundException Class未找到异常
     * @throws NoSuchFieldException 未找到字段异常
     */
    public static Field getField(String className, String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        return getField(ReflectClass.getClass(className), fieldName);
    }
    public static Field[] getFields(Class<?> _class){
        String className = _class.getName();
        if(FIELDS_HASH_MAP.containsKey(className)){
            Fields fields = FIELDS_HASH_MAP.get(className);
            if(fields.isComplete()){
                return fields.getFields();
            }
            else{
                Field[] result = _class.getDeclaredFields();
                fields.addField(result);
                fields.setComplete();
                return result;
            }
        }else{
            Field[] fields = _class.getDeclaredFields();
            Fields fields1 = new Fields(fields,true);
            FIELDS_HASH_MAP.put(className, fields1);
            return fields;
        }
    }

    /**
     *  通过类名获得该类拥有的所有字段
     * @param className 类名
     * @return 字段数组
     * @throws ClassNotFoundException 类未找到异常
     */
    public static Field[] getFields(String className) throws ClassNotFoundException {
        return getFields(ReflectClass.getClass(className));
    }

    /**
     *  通过class实例获得字段
     * @param _class class实例
     * @return Field
     */
    public static Fields getAllFieldToFields(Class<?> _class){
        String className = _class.getName();
        if(FIELDS_HASH_MAP.containsKey(className)){
            Fields fields = FIELDS_HASH_MAP.get(className);
            if (!fields.isComplete()) {
                Field[] result = _class.getDeclaredFields();
                fields.addField(result);
                fields.setComplete();
            }
            return fields;
        }else{
            Field[] fields = _class.getDeclaredFields();
            Fields fields1 = new Fields(fields,true);
            FIELDS_HASH_MAP.put(className, fields1);
            return fields1;
        }
    }
    public static Fields getAllFieldToFields(String className) throws ClassNotFoundException {
        return getAllFieldToFields(ReflectClass.getClass(className));
    }

    //非静态区域==========================================================================================================================

    private Object obj;
    private final Class<?> thisClass;
    private final Field thisField;
    private final boolean isStatic;

    public ReflectField(Object obj, Field field, Class<?> clazz){
        this.obj = obj;
        this.thisField = field;
        this.thisClass = clazz;
        this.isStatic = isStatic(this.thisField);
    }

    public ReflectField(Field field, Class<?> clazz){
        this.thisField = field;
        this.thisClass = clazz;
        this.isStatic = isStatic(this.thisField);
    }

    public Object value() throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            return thisField.get(thisClass);
        }
        return thisField.get(obj);
    }

    public Object value(Object val) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            thisField.set(thisClass, val);
        }
        thisField.set(obj, val);
        return val;
    }
    
    public Object getValueUseObject(Object obj) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            return thisField.get(thisClass);
        }
        return thisField.get(obj);
    }

    public Object setValueUseObject(Object obj, Object val) throws IllegalAccessException {
        thisField.setAccessible(true);
        if (isStatic) {
            thisField.set(thisClass, val);
        }
        thisField.set(obj, val);
        return val;
    }

    /**
     *  获得反射类原始字段实例
     * @return 字段对象
     */
    public Field toField(){
        return this.thisField;
    }

    /**
     *  获得字段名字
     * @return 字段名字
     */
    public String getName(){
        return thisField.getName();
    }

    /**
     *  判断一个字段是否为静态字段
     * @param field 字段
     * @return 是否为静态
     */
    public static boolean isStatic(Field field){
        return Modifier.isStatic(field.getModifiers());
    }
}
