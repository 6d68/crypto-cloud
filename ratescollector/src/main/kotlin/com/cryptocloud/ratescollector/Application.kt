package com.cryptocloud.ratescollector

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
