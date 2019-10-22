package me.doapps.appdhn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by William_ST on 06/10/15.
 */
public class DateUtil {

    public static DateFormat dfStandarDate = new SimpleDateFormat("dd/MM/yyyy");

    public static String getCurrentDate(){
        return dfStandarDate.format(System.currentTimeMillis());
    }

    public static Date getConvertDate(String date, boolean is) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if (is) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        Date result1 = dateFormat.parse(date);
        return result1;
    }
}
