package com.high.baegopa.common.helpers;

import java.net.URLDecoder;

/**
 * Created by high on 2017. 7. 8..
 */
public class GooglePlaceHelper {

    public static final String GEO_METRY =  "http://maps.googleapis.com/maps/api/geocode/json?address=%s&language=ko&sensor=false";
    public static final String PLACE_API_URL = "https://maps.googleapis.com/maps/api/place";


    public static final String API_KEY = "&key=AIzaSyCssWbcXwETRcAg4OwB8-bxc0xfKWVpRzc";

    public static String getNearsearch(Double lat, Double lng){
        String url = PLACE_API_URL + "/nearbysearch/json?location="
                + lat + "," + lng + "&radius=1000&types=food" + API_KEY;
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            return url;
        }
    }

    public static String getDetail(String placeId){
        String url = PLACE_API_URL +"/details/json?placeid="
                + placeId + API_KEY;
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            return url;
        }
    }

    public static String getPlaceImage(String imgRef){
        String url = PLACE_API_URL + "/photo?maxwidth=400&photoreference="
                + imgRef + API_KEY;
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            return url;
        }
    }
}
