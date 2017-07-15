package com.high.baegopa.common.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.high.baegopa.models.LocationCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by high on 2017. 7. 8..
 */
@Slf4j
public class HttpClientProxy{

    private static final int DEFAULT_TIMEOUT = 1000 * 15;

    private static <T> T httpRequest(HttpMethod method, String httpRequest, Class<T> tClass, ParamHelper params, JsonNode json, int connectTimeout, int readTimeout){
        JsonNode result = null;
        try {
            HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory();
            httpFactory.setConnectTimeout(connectTimeout);
            httpFactory.setReadTimeout(readTimeout);
            RestTemplate rtt = new RestTemplate(httpFactory);
            if (HttpMethod.POST.equals(method))
                result = rtt.postForObject(httpRequest, (json != null ? json : params.getMultiValueMap()), JsonNode.class);
            else if (HttpMethod.GET.equals(method))
                result = rtt.getForObject(httpRequest, JsonNode.class);
            else
                log.error("Exception! Unknown request-method is " + method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result json : " + result);

        return JsonHelper.jsonNodeToObject(result, tClass);
    }

    // GET
    public static <T> T httpRequest(String httpRequest, Class<T> tClass) {
        return httpRequest(HttpMethod.GET, httpRequest, tClass, null, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // GET with timeout
    public static <T> T httpRequest(String httpRequest, Class<T> tClass, int connectTimeout, int readTimeout) {
        return httpRequest(HttpMethod.GET, httpRequest, tClass, null, null, connectTimeout, readTimeout);
    }

    // POST
    public static <T> T httpRequest(String httpRequest, Class<T> tClass, ParamHelper params) {
        return httpRequest(HttpMethod.POST, httpRequest, tClass, params, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST with timeout
    public static <T> T httpRequest(String httpRequest, Class<T> tClass, ParamHelper params, int connectTimeout, int readTimeout) {
        return httpRequest(HttpMethod.POST, httpRequest, tClass, params, null, connectTimeout, readTimeout);
    }

    // POST for json data
    public static <T> T httpRequest(String httpRequest, Class<T> tClass, JsonNode json) {
        return httpRequest(HttpMethod.POST, httpRequest, tClass, null, json, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST for json data with timeout
    public static <T> T httpRequest(String httpRequest, Class<T> tClass, JsonNode json, int connectTimeout, int readTimeout) {
        return httpRequest(HttpMethod.POST, httpRequest, tClass, null, json, connectTimeout, readTimeout);
    }

    private static <T> List<T> httpRequestList(HttpMethod method, String httpRequest, Class<T> tClass, ParamHelper params, JsonNode json, int connectTimeout, int readTimeout) {
        JsonNode result = null;
        try {
            HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory();
            httpFactory.setConnectTimeout(connectTimeout);
            httpFactory.setReadTimeout(readTimeout);
            RestTemplate rtt = new RestTemplate(httpFactory);
            if (HttpMethod.POST.equals(method))
                result = rtt.postForObject(httpRequest, (json != null ? json : params.getMultiValueMap()), JsonNode.class);
            else if (HttpMethod.GET.equals(method))
                result = rtt.getForObject(httpRequest, JsonNode.class);
            else
                log.error("Exception! Unknown request-method is " + method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result json : " + result);
        return JsonHelper.jsonNodeToList(result, tClass);
    }

    // GET
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass) {
        return httpRequestList(HttpMethod.GET, httpRequest, tClass, null, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // GET with timeout
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass, int connectTimeout, int readTimeout) {
        return httpRequestList(HttpMethod.GET, httpRequest, tClass, null, null, connectTimeout, readTimeout);
    }

    // POST
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass, ParamHelper params) {
        return httpRequestList(HttpMethod.POST, httpRequest, tClass, params, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST with timeout
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass, ParamHelper params, int connectTimeout, int readTimeout) {
        return httpRequestList(HttpMethod.POST, httpRequest, tClass, params, null, connectTimeout, readTimeout);
    }

    // POST for json data
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass, JsonNode json) {
        return httpRequestList(HttpMethod.POST, httpRequest, tClass, null, json, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST for json data with timeout
    public static <T> List<T> httpRequestList(String httpRequest, Class<T> tClass, JsonNode json, int connectTimeout, int readTimeout) {
        return httpRequestList(HttpMethod.POST, httpRequest, tClass, null, json, connectTimeout, readTimeout);
    }

    private static JsonNode httpRequestToJsonNode(HttpMethod method, String httpRequest, ParamHelper params, JsonNode json, int connectTimeout, int readTimeout) {
        JsonNode result = null;
        try {
            HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory();
            httpFactory.setConnectTimeout(connectTimeout);
            httpFactory.setReadTimeout(readTimeout);
            RestTemplate rtt = new RestTemplate(httpFactory);
            if (HttpMethod.POST.equals(method))
                result = rtt.postForObject(httpRequest, (json != null ? json : params.getMultiValueMap()), JsonNode.class);
            else if (HttpMethod.GET.equals(method))
                result = rtt.getForObject(httpRequest, JsonNode.class);
            else
                log.error("Exception! Unknown request-method is " + method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("result json : " + result);
        return result;
    }

    // GET
    public static JsonNode httpRequestToJsonNode(String httpRequest) {
        return httpRequestToJsonNode(HttpMethod.GET, httpRequest, null, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // GET with timeout
    public static JsonNode httpRequestToJsonNode(String httpRequest, int connectTimeout, int readTimeout) {
        return httpRequestToJsonNode(HttpMethod.GET, httpRequest, null, null, connectTimeout, readTimeout);
    }

    // POST
    public static JsonNode httpRequestToJsonNode(String httpRequest, ParamHelper params) {
        return httpRequestToJsonNode(HttpMethod.POST, httpRequest, params,null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST with timeout
    public static JsonNode httpRequestToJsonNode(String httpRequest, ParamHelper params, int connectTimeout, int readTimeout) {
        return httpRequestToJsonNode(HttpMethod.POST, httpRequest, params,null, connectTimeout, readTimeout);
    }

    // POST for json data
    public static JsonNode httpRequestToJsonNode(String httpRequest, JsonNode json) {
        return httpRequestToJsonNode(HttpMethod.POST, httpRequest, null, json, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    // POST for json data with timeout
    public static JsonNode httpRequestToJsonNode(String httpRequest, JsonNode json, int connectTimeout, int readTimeout) {
        return httpRequestToJsonNode(HttpMethod.POST, httpRequest, null, json, connectTimeout, readTimeout);
    }
}
