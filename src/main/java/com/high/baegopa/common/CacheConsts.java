package com.high.baegopa.common;

/**
 * Created by high on 2017. 7. 4..
 */
public class CacheConsts {
    private enum Prefix {
        USER_RECOMMEND, RESTAURANT_GRADE;
    }

    private static String getPrefix(Prefix prefix, String subKey) {
        return prefix + ":" + subKey;
    }

    public static class UserRecommend {
        Prefix prefix = Prefix.USER_RECOMMEND;
        public final String listByUserAndWhether = getPrefix(prefix, "USER:WHETHER:%s:%s");
        public final String listByUserAndFoodType = getPrefix(prefix, "USER:FOOD_TYPE:%s:%s");
    }

    public static class RestaurantGrade {
        Prefix prefix = Prefix.RESTAURANT_GRADE;
        public final String listByLocationCode = getPrefix(prefix, "LOCATION_CODE:%s");
        public final String listByLocationCodeAndFoodType = getPrefix(prefix, "LOCATION_CODE:FOOD_TYPE:%s:%s");
    }
}
