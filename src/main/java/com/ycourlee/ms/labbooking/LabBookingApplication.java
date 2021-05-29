package com.ycourlee.ms.labbooking;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiangyong
 */
@EnableSwagger2Doc
@SpringBootApplication
public class LabBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabBookingApplication.class, args);
    }
}
