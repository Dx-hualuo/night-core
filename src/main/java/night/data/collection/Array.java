package night.data.collection;

import night.reflect.ReflectArray;

import java.util.Arrays;

/**
 *  Array数组工具类
 * @author dx_hualuo
 */
@SuppressWarnings({"unchecked"})
public class Array {
    /**
     *  将Integer数组转为int数组
     * @param array Integer数组
     * @return int数组
     */
    public static int[] unbox(Integer[] array){
        return Arrays.stream(array).mapToInt(Integer::intValue).toArray();
    }
    /**
     *  将Double数组转为double数组
     * @param array Double数组
     * @return double数组
     */
    public static double[] unbox(Double[] array){
        return Arrays.stream(array).mapToDouble(Double::shortValue).toArray();
    }
    /**
     *  将Long数组转为long数组
     * @param array Long数组
     * @return long数组
     */
    public static long[] unbox(Long[] array){
        return Arrays.stream(array).mapToLong(Long::longValue).toArray();
    }
    /**
     *  将Character数组转为char数组
     * @param array Character数组
     * @return char数组
     */
    public static char[] unbox(Character[] array){
        char[] chars = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] = array[i];
        }
        return chars;
    }
    /**
     *  将Byte数组转为byte数组
     * @param array Byte数组
     * @return byte数组
     */
    public static byte[] unbox(Byte[] array){
        byte[] chars = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] = array[i];
        }
        return chars;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] subArray(T[] array, int startIndex, int endIndex){
        int length;
        if((length = (endIndex - startIndex)) < 0){
            throw new IndexOutOfBoundsException("截取长度有误！");
        }
        if(length == 0){
            return (T[])((new ReflectArray<>((Class<T>) ReflectArray.getArrayItemType(array), 0)).getObject());
        }
        ReflectArray<T> newArr = new ReflectArray<>((Class<T>) ReflectArray.getArrayItemType(array), length);
        T[] tArray = (T[])newArr.getObject();
        System.arraycopy(array, startIndex, tArray, 0, length);
        return tArray;
    }

    /**
     *  更改已知数组的长度
     * @param array 已知数组
     * @param newLength 新长度
     * @param <T> 数组类型
     * @return 新长度为长度的数组
     */
    public static <T> T[] changeLength(T[] array, int newLength) {
        Class<?> componentType = ReflectArray.getArrayItemType(array);
        int length = java.lang.reflect.Array.getLength(array);
        T[] newArray = (T[])java.lang.reflect.Array.newInstance(componentType, newLength);
        System.arraycopy(array,0, newArray,0, length);
        return newArray;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Byte[] box(byte[] array) {
        int length = array.length;
        Byte[] dest = new Byte[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Float[] box(float[] array) {
        int length = array.length;
        Float[] dest = new Float[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     *
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Double[] box(double[] array) {
        int length = array.length;
        Double[] dest = new Double[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     *
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Boolean[] box(boolean[] array) {
        int length = array.length;
        Boolean[] dest = new Boolean[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Long[] box(long[] array) {
        int length = array.length;
        Long[] dest = new Long[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Character[] box(char[] array) {
        int length = array.length;
        Character[] dest = new Character[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Integer[] box(int[] array) {
        int length = array.length;
        Integer[] dest = new Integer[length];
        for (int i = 0; i < length; i++) {
            dest[i] = array[i];
        }
        return dest;
    }

    /**
     * 将基本类型数组转换为对应的包装类型数组
     * @param array 基本数据类型数组
     * @return 包装类型数组
     */
    public static Short[] box(short[] array) {
        int len = array.length;
        Short[] dest = new Short[len];
        for (int i = 0; i < len; i++) {
            dest[i] = array[i];
        }
        return dest;
    }
}
