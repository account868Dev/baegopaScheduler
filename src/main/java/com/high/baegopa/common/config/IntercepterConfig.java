package com.high.baegopa.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by high on 2017. 7. 2..
 */
@Configuration
@EnableWebMvc
public class IntercepterConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    private AuthIntercepterHandler authIntercepterHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authIntercepterHandler);
    }
}