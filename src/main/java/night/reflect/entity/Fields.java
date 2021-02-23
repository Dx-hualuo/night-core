package night.reflect.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author dx_hualuo
 */
public class Fields implements Serializable {
    private boolean complete = false;
    private final HashMap<String, Field> field;

    /**
     *  创建字段？，这个方法不应该在此调用
     */
    public Fields() {
        this.field = new HashMap<>();
    }

    /**
     *  通过字段创建
     * @param field 字段
     */
    public Fields(Field field) {
        this.field = new HashMap<>();
        addField(field);
    }

    /**
     *  初始化一个完整类方法
     * @param field 字段
     * @param complete 是否是完整的
     */
    public Fields(Field[] field, boolean complete) {
        this.field = new HashMap<>();
        addField(field);
        this.complete = complete;
    }

    /**
     *  判断是否为一个类的完整字段列表
     * @return boolean
     */
    public boolean isComplete(){
        return complete;
    }

    public void setComplete(){
        complete = true;
    }

    public Field getField(String fieldName) throws NoSuchFieldException {
        Field field = this.field.get(fieldName);
        if(field == null && this.complete){
            throw new NoSuchFieldException();
        }
        return field;
    }

    public Field[] getFields(){
        Field[] result = new Field[field.size()];
        field.values().toArray(result);
        return result;
    }

    public void addField(Field field){
        if(isComplete()){
            return;
        }
        this.field.put(field.getName(), field);
    }

    public void addField(Field[] field){
        for (Field me: field) {
            addField(me);
        }
    }

    /**
     *  获得所有字段的名字
     * @return 字符串数组
     */
    public String[] getNames() {
        Field[] field = getFields();
        String[] names = new String[field.length];
        for (int i = 0; i < field.length; i++) {
            names[i] = field[i].getName();
        }
        return names;
    }
}
