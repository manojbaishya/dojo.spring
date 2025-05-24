package org.dojo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDojo {
    public static void main(String[] args) {
        SpringApplication.run(SpringDojo.class, args);
    }
}
