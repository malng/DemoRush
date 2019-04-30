package design.maliang.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * 构造方法私有
 * 成员方法静态
 */
public class DateUtil {
    private DateUtil() { }

    /**
     * 把日期转换成指定格式的字符串
     */
    public static String dateToString(Date date , String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 把指定格式的字符串转化为日期
     */
    public static Date stringToDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

}
