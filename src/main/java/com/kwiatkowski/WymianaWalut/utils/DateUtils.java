package com.kwiatkowski.WymianaWalut.utils;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtils {

    public static boolean isAdult(Date birthday)
    {
        DateTime now = DateTime.now();
        DateTime then = new DateTime(birthday.getTime());
        int years = Years.yearsBetween(then, now).getYears();

        if(years < 18)
        {
            return false;
        }

        return true;
    }
}
