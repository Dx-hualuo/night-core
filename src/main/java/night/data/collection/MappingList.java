package night.data.collection;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *  简单包装的一键多值的集合，效率未知
 * @author dx_hualuo
 * @param <key> 键类型
 * @param <value> 值类型
 */
public class MappingList<key, value> implements Serializable {
    private final HashMap<key, List<value>> map = new HashMap<>();
    @JSONField(serialize = false)
    private final List<value> values = new ArrayList<>();
    public MappingList(){}

    /**
     *  向MappingList加入键值
     * @param k 键
     * @param v 值
     * @return 是否添加成功
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean put(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            if (!val.contains(v)) {
                val.add(v);
                return true;
            }
        }
        else{
            List<value> val = new ArrayList<>();
            val.add(v);
            map.put(k,val);
            return true;
        }
        return false;
    }

    /**
     *  通过k获得值
     * @param k 键
     * @return 值
     */
    public List<value> get(key k){
        if(!map.containsKey(k)){
            return null;
        }
        return map.get(k);
    }

    /**
     *  获得k键里的第index个值
     * @param k 键
     * @param index 下标
     * @return 值
     */
    public value get(key k, int index){
        if(!map.containsKey(k)){
            return null;
        }
        List<value> val = map.get(k);
        if (val.size() > index) {
            return val.get(index);
        }
        return null;
    }

    /**
     *  检查里面是否有这个键
     * @param k 键
     * @return 是否有键
     */
    public boolean containsKey(key k){
        return map.containsKey(k);
    }

    /**
     *  检查k键里面是否有v这个值
     * @param k 键
     * @param v 值
     * @return 是否有值
     */
    public boolean containsValue(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            return val.contains(v);
        }
        return false;
    }

    /**
     *  获得k键下值的数量
     * @param k 键
     * @return 值的数量
     */
    public int getValueCountByKey(key k){
        if(map.containsKey(k)){
            List<value> val = map.get(k);
            return val.size();
        }
        return 0;
    }

    /**
     *  键的set集合
     * @return set集合
     */
    public Set<key> keySet(){
        return map.keySet();
    }

    /**
     *  获得键的个数
     * @return 键的个数
     */
    public int keyCount(){
        return map.size();
    }

    /**
     *  获得所有值的个数
     * @return 个数
     */
    public int valueCount(){
        return values.size();
    }

    public List<value> valueList(){
        return values;
    }

    /**
     *  获得键的个数
     * @return 键的个数
     */
    public int size(){
        return map.size();
    }

    /**
     *  删除k键，包括他的所有值
     * @param k 键
     * @return 是否移除成功
     */
    public boolean removeKey(key k){
        if (map.containsKey(k)) {
            map.remove(k);
            return true;
        }
        return false;
    }

    /**
     *  移除指定键下的指定值
     * @param k 键
     * @param v 值
     * @return 是否移除成功
     */
    public boolean removeValue(key k, value v){
        if (map.containsKey(k)) {
            List<value> val = map.get(k);
            if(val.contains(v)){
                val.remove(v);
                return true;
            }
        }
        return false;
    }
}
