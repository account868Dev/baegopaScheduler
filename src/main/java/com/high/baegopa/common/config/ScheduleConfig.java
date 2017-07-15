package com.high.baegopa.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

/**
 * Created by high on 2017. 7. 3..
 */
@Configuration
public class ScheduleConfig {
    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutorService() {
        ScheduledExecutorFactoryBean scheduledExecutorBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorBean.setPoolSize(5);
        return scheduledExecutorBean;
    }
}
