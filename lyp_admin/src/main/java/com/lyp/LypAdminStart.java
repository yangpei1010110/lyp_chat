package com.lyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @CREATE_DATE 2019_11_1 14:25
 * @AUTHOR LiYangPei
 */
@SpringBootApplication(scanBasePackages = "com.lyp.*")
@ServletComponentScan(basePackages = "com.lyp.filter")
@EnableEurekaClient
@Cacheable
public class LypAdminStart {
    public static void main(String[] args) {
        SpringApplication.run(LypAdminStart.class, args);
    }
}
