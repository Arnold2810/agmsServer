package kr.co.uxn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class agmsCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(agmsCommonApplication.class, args);
    }
}