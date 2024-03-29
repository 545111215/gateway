package org.yugh.coral.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * //测试 服务 1
 *
 * @author: 余根海
 * @creation: 2019-04-09 18:17
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@EnableHystrix
@EnableFeignClients
//@EnableFeignAcceptGzipEncoding
@ComponentScan({"org.yugh.coral"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }


}
