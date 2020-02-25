package com.atguigu.scw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class ScwWebuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwWebuiApplication.class, args);
	}

}
