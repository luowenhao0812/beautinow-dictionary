package com.tempetek.dictionary.server;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.tempetek"}, exclude = SeataAutoConfiguration.class)
@EnableFeignClients
@EnableSwagger2Doc
@EnableDiscoveryClient
@MapperScan("com.tempetek.dictionary.server.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
