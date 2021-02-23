package night.data;

/**
 *  字符工具
 */
public class Char {
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }
}