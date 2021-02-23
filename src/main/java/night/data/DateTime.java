package night.data;

import java.util.Date;

/**
 *  对于日期的操作
 * @author dx_hualuo
 */
public class DateTime {
    /**
     *  格式化Date对象
     * @param date date对象
     * @param format 格式
     * @return 格式化的字符串
     */
    public static String format(Date date, String format){
        return PooledDateFormat.toString(format, date);
    }

    /**
     *  将字符串转换为Date对象
     * @param date 字符串类型的时间
     * @param format 格式
     * @return Date对象
     */
    public static Date toDate(String date, String format){
        return PooledDateFormat.toDate(format, date);
    }

    /**
     *  格式化就完了，不指定格式
     * @param date 日期
     * @return String类型的日期
     */
    public static String format(Date date){
        return format(date,"yyyy-MM-dd HH:mm:ss.SSS");
    }
}
