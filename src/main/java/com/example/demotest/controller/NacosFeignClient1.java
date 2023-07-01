package com.example.demotest.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("nacos-demo1")
public interface NacosFeignClient1 {
    @GetMapping("/test")
    public String test();
}
