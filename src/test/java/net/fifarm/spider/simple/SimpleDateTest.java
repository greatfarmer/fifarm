package net.fifarm.spider.simple;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateTest {

    @Test
    public void getDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d));
    }

}
