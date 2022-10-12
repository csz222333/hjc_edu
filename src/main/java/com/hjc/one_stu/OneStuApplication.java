package com.hjc.one_stu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author csz
 */
@SpringBootApplication
@MapperScan("com.hjc.one_stu.Mapper")
public class OneStuApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneStuApplication.class, args);
    }

}
