package com.dfd.vueblog_java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dfd"})
@MapperScan("com.dfd.vueblog_java.mapper")
@EnableTransactionManagement
@Configuration
public class VueblogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueblogJavaApplication.class, args);
    }

}
