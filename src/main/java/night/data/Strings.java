package night.data;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 *  专门处理设置
 * @author dx_hualuo
 */
public class Strings {
    /**
     *      使字符串开头大写
     * @param str 需要大写的字符串
     * @return 大写后的字符串
     */
    public static String headUpperCase(String str){
        if (str == null){
            throw new NullPointerException();
        }else if(str.length() == 0){
            return "";
        }
        String result = str;
        char head = str.charAt(0);
        if(head > 96 && head < 123){
            result = ((char)((byte) head- 32)) + str.substring(1);
        }
        return result;
    }

    /**
     *      把UTF-8转为网页Url编码方式
     * @param str 需要转换编码的字符串
     * @return  转换后的字符串
     */
    public static String toWebUrlEncoding(String str){
        return new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }
    /**
     *      把网页Url编码方式转为UTF-8
     * @param str 需要转换编码的字符串
     * @return  转换后的字符串
     */
    public static String WebEncodingToUTF_8(String str){
        if(str == null){
            throw new NullPointerException();
        }
        return new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    /**
     *      把指定编码的字符串转换为ISO-8859-1
     * @param charset   指定编码方式
     * @param str   需要转换编码的字符串
     * @return  转换后的字符串
     * @throws UnsupportedEncodingException 给定的编码方式字符串有误将触发
     */
    public static String toISO_8859_1(String charset, String str) throws UnsupportedEncodingException {
        if(str == null){
            throw new NullPointerException();
        }
        return new String(str.getBytes(charset), StandardCharsets.ISO_8859_1);
    }
}
