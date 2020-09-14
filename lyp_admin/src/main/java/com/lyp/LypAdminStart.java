package com.lyp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@Slf4j
@SpringBootApplication(scanBasePackages = "com.lyp.*")
@ServletComponentScan(basePackages = "com.lyp.filter")
@EnableEurekaClient
@Cacheable
public class LypAdminStart {
    public static void main(String[] args) {
        SpringApplication.run(LypAdminStart.class, args);
    }
}
