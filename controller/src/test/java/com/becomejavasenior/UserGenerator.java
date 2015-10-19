package com.becomejavasenior;

import java.util.Calendar;

/**
 * Created by pyavchik.a on 26.09.15.
 */
public class UserGenerator {

    private String name;
    private String tag;
    private String jobPosition;
    private String phoneNumber;
    private String email;
    private String skypeLogin;
    private String messageText;

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSkypeLogin() {
        return skypeLogin;
    }

    public String getMessageText() {
        return messageText;
    }

    public UserGenerator(){

        Calendar calendar = Calendar.getInstance();

        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String millisecond = String.valueOf(calendar.get(Calendar.MILLISECOND));

        name = "test";

        tag = "#" + year + month + hour + minute + millisecond;

        jobPosition = "jobPos";

        phoneNumber =  year + month + hour + minute + millisecond;

        email = "t" + year + month + hour + minute + millisecond + "@ya.ru";

        skypeLogin =  "skype" + year + month + hour + minute + millisecond;

        messageText =  "message" + year + month + hour + minute + millisecond;

    }


}
