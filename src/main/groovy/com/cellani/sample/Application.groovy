package com.cellani.sample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class Application {

    static void main(String[] args) {
        SpringApplication.run Application, args
    }
}
