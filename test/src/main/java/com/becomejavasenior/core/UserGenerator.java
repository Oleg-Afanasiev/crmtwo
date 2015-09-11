package com.becomejavasenior.core;

import java.util.Calendar;


/**
 * Created by pyavchik.a on 11.09.15.
 */
public class UserGenerator {

    Calendar calendar = Calendar.getInstance();

    String year = String.valueOf(calendar.get(Calendar.YEAR));
    String month = String.valueOf(calendar.get(Calendar.MONTH));
    String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    String minute = String.valueOf(calendar.get(Calendar.MINUTE));
    String millisecond = String.valueOf(calendar.get(Calendar.MILLISECOND));

    public String name = "testNameUser" + year + month + hour + minute + millisecond;

    public String tag = "testTag" + year + month + hour + minute + millisecond;

    public String jobPosition = "testTag" + year + month + hour + minute + millisecond;

    public String phoneNumber =  year + month + hour + minute + millisecond;

    public String email = "t" + year + month + hour + minute + millisecond + "@ya.ru";

    public String skypeLogin =  "testTag" + year + month + hour + minute + millisecond;

    public String messageText =  "testTag" + year + month + hour + minute + millisecond;

}
