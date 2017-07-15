package com.high.baegopa.common.helpers;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by high on 2017. 7. 4..
 */
@Slf4j
public class CommonHelper {

    public static boolean isNumeric(String s) {
        return (s != null) ? s.matches("[-+]?\\d*\\.?\\d+") : false;
    }

    public static int getRandInt(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }

    public static int getRandTen() {
        return (int) (System.currentTimeMillis() % 10);
    }

    public static String stringFormat(String format, Object... args) {
        try {
            return String.format(format, args);
        } catch (Exception e) {
            StringBuilder strArgs = new StringBuilder();
            for (Object arg : args)
                strArgs.append(arg + "/");
            e.printStackTrace();
            log.error(e.getClass().getSimpleName() + "! string format, message = " + e.fillInStackTrace() + ", format=" + format + ", args=" + strArgs);
        }
        return null;
    }
}
