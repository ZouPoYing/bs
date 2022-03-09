package com.graduation.bs.utils;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isPhone(String str) {
        String reg = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";// 手机号正则
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isNotPhone(String str) {
        return !isPhone(str);
    }

    public static boolean isNum(String str) {
        String reg = "^[0-9]*[1-9][0-9]*$";// 数字正则
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isPic(String str) {
        String reg = ".*/.*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static Object formatTime(Object date) {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sj.format(date);
    }

}
