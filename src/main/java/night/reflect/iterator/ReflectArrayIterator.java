package night.reflect.iterator;

import night.reflect.ReflectArray;
import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author 17868
 */
@SuppressWarnings("unchecked")
public class ReflectArrayIterator<T> extends ReflectArray<T> implements Iterator<T> {
    private int index = 0;
    private final int length;

    public ReflectArrayIterator(Object obj) {
        super(obj);
        this.length = Array.getLength(obj);
    }

    @Override
    public boolean hasNext() {
        return index < length;
    }

    @Override
    public T next() {
        return (T)get(index++);
    }
}
