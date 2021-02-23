package night.reflect;

import night.reflect.iterator.ReflectArrayIterator;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author dx_hualuo
 */
@SuppressWarnings({"UnusedReturnValue", "unchecked"})
public class ReflectArray<T> extends ReflectObject implements Iterable<T>, Serializable {

    public ReflectArray(String className, int... length) throws ClassNotFoundException {
        this(ReflectClass.getClass(className), length);
    }

    public ReflectArray(String className, int length) throws ClassNotFoundException {
        this(ReflectClass.getClass(className), length);
    }

    public ReflectArray(Class<T> _class, int... length){
        super(Array.newInstance(_class, length));
    }

    public ReflectArray(Class<T> _class, int length){
        super(Array.newInstance(_class, length));
    }

    public ReflectArray(ReflectObject obj){
        super(obj.getObject());
    }

    public ReflectArray(Object object){
        super(object);
    }

    public Object get(int index){
        return Array.get(obj, index);
    }

    public Object get(int... index){
        Object object = obj;
        for (int in: index) {
            object = Array.get(object, in);
        }
        return object;
    }

    public Object set(int index, Object obj){
        Array.set(super.obj, index, obj);
        return obj;
    }

    public Object set(Object obj, int... index){
        Object object = super.obj;
        for (int i = 0; i < index.length; i++) {
            if(index.length - 1 == i){
                Array.set(object, index[i], obj);
            }else{
                Array.get(object, index[i]);
            }
        }
        return obj;
    }

    /**
     *  获得长度
     * @return 长度
     */
    public int length(){
        return Array.getLength(obj);
    }

    /**
     *  获得数组的数组维度深度
     * @return 数组维度深度
     */
    public int getDim(){
        int dim = 0;
        Class cls = obj.getClass();
        while (cls.isArray()) {
            dim++;
            cls = cls.getComponentType();
        }
        return dim;
    }

    /**
     *  获得该数组的元素类型
     * @return 元素类型
     */
    public Class<?> getComponentType(){
        return thisClass.getComponentType();
    }

    @Override
    public Iterator<T> iterator() {
        return new ReflectArrayIterator<>(obj);
    }

    @Override
    public void forEach(Consumer action) {
        new ReflectArrayIterator<>(obj).forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new RuntimeException("该方法未实现！");
    }

    /**
     *  获得元素类型
     * @param object 数组
     * @return 元素类型
     */
    public static Class<?> getArrayItemType(Object object){
        return object.getClass().getComponentType();
    }
}
