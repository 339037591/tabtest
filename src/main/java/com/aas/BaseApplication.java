package com.aas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.aas.dao")
@EnableScheduling
public class BaseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
    
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BaseApplication.class);
    }

}
