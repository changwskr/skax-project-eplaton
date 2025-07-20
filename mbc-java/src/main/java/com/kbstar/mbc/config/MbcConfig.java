package com.kbstar.mbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MBC Application Configuration
 * 
 * @author KBSTAR
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement
@ImportResource({
        "classpath:config/mbc/spring-context.xml",
        "classpath:config/mbc/database-context.xml"
})
public class MbcConfig {

    // Additional configuration beans can be added here

}