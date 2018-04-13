/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.microservicespring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 *
 * @author UNGERW
 */
@EnableCircuitBreaker
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class ProductionOrderApp {
    
    public static void main(String[] args) {
		SpringApplication.run(ProductionOrderApp.class, args);
	}
    
}
