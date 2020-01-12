package com.weatherchecker.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateProvider {

    public static String getDayOfWeek() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek){
            case 2: return "Monday";
            case 3: return "Tueasday";
            case 4: return "Wednesday";
            case 5: return "Thursday";
            case 6: return "Friday";
            case 7: return "Saturday";
            case 1: return "Sunday";
            default: return "";
        }
    }

    public static String getDate() {
        Date date = Calendar.getInstance().getTime();
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }
}
