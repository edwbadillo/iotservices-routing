package com.edwin.iot.iotspringgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class IotSpringGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(IotSpringGatewayApplication.class, args);
	}
}
