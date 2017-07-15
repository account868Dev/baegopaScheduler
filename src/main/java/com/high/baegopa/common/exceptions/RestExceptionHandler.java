package com.high.baegopa.common.exceptions;

import com.high.baegopa.models.response.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by high on 2017. 7. 2..
 */
@EnableWebMvc
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public CommonResponse handleControllerException(HttpServletRequest req, Throwable ex) {
        return CommonResponse.getInstance().fail(ex.getMessage());
    }
}