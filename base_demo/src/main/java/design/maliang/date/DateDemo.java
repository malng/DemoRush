package design.maliang.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

    /**
     * Date()
     * 当前时间的对象。
     */
    @Test
    public void Constructor1(){
        Date date = new Date();
        System.out.println(date);
    }

    /**
     * Date(long date)
     * 表示自从标准基准时间（称为“历元（epoch）”，
     * 即 1970 年 1 月 1 日 00:00:00 GMT）以来的指定毫秒数。
     */
    @Test
    public void Constructor2(){
        long longNum = 86400*365;
        Date date = new Date(longNum);
        System.out.println(date);
    }

    //long getTime()
    @Test
    public void getTime(){
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
    }

    //void setTime(long time)
    @Test
    public void setTime(){
        Date date = new Date();
        long longNum = 86400*365;
        date.setTime(longNum);
        System.out.println(date);
        //Thu Jan 01 16:45:36 CST 1970
    }

    //Date-->String
    @Test
    public void dateToString(){
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat();
        String formatDate = sdf.format(date);
        System.out.println(formatDate);
        //19-4-30 下午12:54

        sdf=new SimpleDateFormat("yyyy年mm月dd日 hh:mm:ss");
        formatDate = sdf.format(date);
        System.out.println(formatDate);
        //2019年57月30日 12:57:50

    }

    //String-->Date
    @Test
    public void stringToDate() throws ParseException {
        String dataStr = "2080-09-09 12:23:43";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parseDate = sdf.parse(dataStr);
        System.out.println(parseDate);
        //Mon Sep 09 00:23:43 CST 2080
    }


}
