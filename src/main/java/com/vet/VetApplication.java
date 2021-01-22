package com.vet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@SpringBootApplication
public class VetApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetApplication.class, args);
    }

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }
}
