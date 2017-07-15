package com.high.baegopa.common.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by high on 2017. 7. 2..
 */
public class FilterConfig implements Filter{

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

        HttpServletResponse respon = (HttpServletResponse) response;
        respon.setHeader("Access-Control-Allow-Origin", "*");
        respon.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
        respon.setHeader("Access-Control-Max-Age", "3600");
        respon.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Descriptionx-requested-with, origin, content-type, accept");

        chain.doFilter(request, respon);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}