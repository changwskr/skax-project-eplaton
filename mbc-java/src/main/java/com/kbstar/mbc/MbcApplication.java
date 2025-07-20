package com.kbstar.mbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * MBC Spring Boot Application
 * 
 * @author KBSTAR
 * @version 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.kbstar.mbc",
        "com.kbstar.mbc.as",
        "com.kbstar.mbc.dc",
        "com.kbstar.mbc.ic",
        "com.kbstar.mbc.pc"
})
public class MbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbcApplication.class, args);
    }
}