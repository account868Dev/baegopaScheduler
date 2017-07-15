package com.high.baegopa;

import com.high.baegopa.common.config.DBProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources(value = @PropertySource("classpath:application.properties"))
@EnableConfigurationProperties({DBProperties.class})
@SpringBootApplication
public class BaegopaSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaegopaSchedulerApplication.class, args);
	}
}
