package com.xf.oschina.utils;

import java.util.Calendar;

public class TimeUtils {

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour + ":" + minute;
    }
}
