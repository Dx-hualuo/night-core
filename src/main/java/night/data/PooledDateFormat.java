package night.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  具有池特性的日期格式化(多线程可用)
 *      解决SimpleDateFormat使用要频繁创建，是Format使用具有池的特性
 * @author dx_hualuo
 */
public class PooledDateFormat {
    private static final Map<String, ConcurrentLinkedQueue<SimpleDateFormat>> formatPool = new HashMap<>();

    /**
     *  将date以formatString提供的格式格式化
     * @param formatString 格式化格式
     * @param date 日期
     * @return String
     */
    public static String toString(String formatString, Date date){
        ConcurrentLinkedQueue<SimpleDateFormat> simpleDateFormats;
        SimpleDateFormat format;
        String result;
        if (formatPool.containsKey(formatString)) {
            simpleDateFormats = formatPool.get(formatString);
            if ((format = simpleDateFormats.poll()) == null) {
                format = new SimpleDateFormat(formatString);
                result = format.format(date);
                simpleDateFormats.offer(format);
                return result;
            }
            result = format.format(date);
            simpleDateFormats.offer(format);
            return result;
        }
        simpleDateFormats = new ConcurrentLinkedQueue<>();
        formatPool.put(formatString,simpleDateFormats);
        format = new SimpleDateFormat(formatString);
        result = format.format(date);
        simpleDateFormats.offer(format);
        return result;
    }

    /**
     *  将字符串通过给定格式转换为Date
     * @param formatString 格式化格式
     * @param dateString 日期字符串
     * @return Date
     */
    public static Date toDate(String formatString, String dateString){
        ConcurrentLinkedQueue<SimpleDateFormat> simpleDateFormats;
        SimpleDateFormat format;
        Date result;
        if (formatPool.containsKey(formatString)) {
            simpleDateFormats = formatPool.get(formatString);
            if ((format = simpleDateFormats.poll()) == null) {
                format = new SimpleDateFormat(formatString);
                try {
                    result = format.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                simpleDateFormats.offer(format);
                return result;
            }
            try {
                result = format.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            simpleDateFormats.offer(format);
            return result;
        }
        simpleDateFormats = new ConcurrentLinkedQueue<>();
        formatPool.put(formatString,simpleDateFormats);
        format = new SimpleDateFormat(formatString);
        try {
            result = format.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        simpleDateFormats.offer(format);
        return result;
    }
}
