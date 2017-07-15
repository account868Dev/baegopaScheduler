package com.high.baegopa.models.response;

import lombok.Data;

/**
 * Created by high on 2017. 7. 2..
 */
@Data
public class CommonResponse<T> {

    public static CommonResponse getInstance(){
        return new CommonResponse();
    }

    private String result;
    private String reason;
    private T data;

    public CommonResponse fail(String msg){
        this.result = "fail";
        this.reason = msg;
        return this;
    }

    public CommonResponse success(){
        this.result = "success";
        return this;
    }

    public CommonResponse success(T data){
        this.result = "success";
        this.data = data;
        return this;
    }
}
