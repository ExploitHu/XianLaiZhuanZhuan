package biao.community.time;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class GetTime {

    //返回时间的格式"yyyy-MM-dd HH:mm:ss"
    public static String getWebsiteDatetime(String webUrl) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反格式化时间例如  2021-11-02 16:15:12 [中国科学院国家授时中心]  -》  20211102161512
    public static String antonymFormatTime(String time) {
        String str = "";

        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) >= '0' && time.charAt(i) <= '9') {
                str = str + time.charAt(i);
            }
        }

        return str;
    }

    //格式化时间例如 20211102161512  -》  2021-11-02 16:15:12
    public static String formatTime(String time) {
        String str = "";

        str += time.substring(0, 4) + "-";
        str += time.substring(4, 6) + "-";
        str += time.substring(6, 8) + " ";
        str += time.substring(8, 10) + ":";
        str += time.substring(10, 12) + ":";
        str += time.substring(12, 14);

        return str;
    }

    /***
     *检查时间格式是否正确  年月日
     * @param time      传入时间  例：20210228 or 2021-02-28
     * @param format    传入格式  例：yyyy-MM-dd or yyyyMMdd
     * @return
     */
    public static boolean examineYMD(String time, String format) {
        if (time == null || time.isEmpty() || format == null || format.isEmpty()) {
            return false;
        }

        if (format.replaceAll("'.+?'", "").indexOf("y") < 0) {
            format += "/yyyy";
            DateFormat formatter = new SimpleDateFormat("/yyyy");
            time += formatter.format(new Date());
        }

        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(time, pos);

        if (date == null || pos.getErrorIndex() > 0) {
            return false;
        }
        if (pos.getIndex() != time.length()) {
            return false;
        }

        if (formatter.getCalendar().get(Calendar.YEAR) > 9999) {
            return false;
        }

        return true;
    }





}
