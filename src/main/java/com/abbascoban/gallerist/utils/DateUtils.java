package com.abbascoban.gallerist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getCurrentDate(Date date) {

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);

    }


}

