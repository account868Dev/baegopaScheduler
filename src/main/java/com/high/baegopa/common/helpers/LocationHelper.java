package com.high.baegopa.common.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by high on 2017. 7. 8..
 */
public class LocationHelper {

    public final static String PUBLIC_KEY = "ServiceKey=jS1heVGQzXhGff%2BseSOlm422QtXijhCC7diJnIHMg%2BGlhwZJHkVl92J3IRNtFq415WfqLlL4sKLFlThW6UZFhA%3D%3D";
    public final static String PUBLIC_LOCAL_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
    public final static String TYPE_JSON = "&_type=json";
    public final static String DEVICE = "&MobileOS=ETC&MobileApp=GAMSEONG";
    public final static String RESULT_NUM = "&numOfRows=";
    public final static String PAGE_NO = "&pageNo=";
    public final static String AREA_CODE = "&areaCode=";

    public static String areaCodeUrl(int resultNum, int pageNo) {
        String url = PUBLIC_LOCAL_URL + "areaCode?" + PUBLIC_KEY + RESULT_NUM  + resultNum
                +PAGE_NO + pageNo + DEVICE + TYPE_JSON;
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            return url;
        }
    }

    public static String areaLocalCodeUrl(int resultNum, int pageNo, int areaCode) {
        String url = PUBLIC_LOCAL_URL + "areaCode?" + PUBLIC_KEY + RESULT_NUM  + resultNum
                +PAGE_NO + pageNo + AREA_CODE + areaCode + DEVICE + TYPE_JSON;
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            return url;
        }
    }
}
