package com.pim.client.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IMTimeUtils {

    /*
     * Convert time to timestamp
     */
    public static String dateToStamp(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(s)) {
            try {
                res = String.valueOf(sdf.parse(s).getTime() / 1000);
            } catch (Exception e) {
            }
        } else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time / 1000);
        }
        return res;
    }

    /*
     * Convert timestamp to time
     */
    public static String stampToDate(int time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(time * 1000L));
        return times;
    }

    /*
     * Get the current time
     */
    public static String getDateTime() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }

    /**
     * Get the nanosecond time
     * @return
     */
    public static long getNanoTime() {
        return System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L;
    }

    public static String getTimeSt() {
        return System.currentTimeMillis() + "";
    }
}