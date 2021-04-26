package com.chauyiu1994.livibankans;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LivibankAnsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(LivibankAnsApplication.class)
                .properties("spring.config.name:application,liviBankAns")
                .build()
                .run(args);
    }

}
