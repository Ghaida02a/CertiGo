package com.codeline.CertiGo.Helper;
import java.util.List;

public class Utils {
    public static <T> boolean isNull(T str) {
        return str == null;
    }

    public static <T> boolean isNotNull(T str) {
        return !isNull(str);
    }

    public static <T> Boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean isListNotEmpty(List<T> integerList) {
        return !isListEmpty(integerList);
    }

    public static <T> boolean isBlank(T str) {
        return isNull(str) || str.toString().trim().isEmpty();
    }

    public static <T> boolean isNotBlank(T str) {
        return !isBlank(str);
    }

}
