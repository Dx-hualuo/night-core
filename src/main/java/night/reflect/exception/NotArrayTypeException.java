package night.reflect.exception;

/**
 *  不是一个数组类型异常
 * @author night
 */
public class NotArrayTypeException extends RuntimeException {
    public NotArrayTypeException(Class<?> message) {
        super(message.getName() + "Not an array!");
    }
}
