package com.ch.virtual.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories
@SpringBootApplication
public class VirtualWalletApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(VirtualWalletApplication.class, args);
    }
}