package com.example.demotest;

import com.example.demotest.controller.NacosFeignAutoConfiguration;
//import com.samples.province.common.NacosFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
//@EnableDiscoveryClient
//@EnableFeignClients(basePackageClasses = {NacosFeignClient.class})
@EnableFeignClients(basePackages = {"com.samples.province.common","com.example.demotest"})
@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@RibbonClient(value = "nacos-demo",configuration = {NacosFeignAutoConfiguration.class})
public class DemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b(){
        a();
        return new B();
    }
}
