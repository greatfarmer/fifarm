package net.fifarm.spider.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

}
