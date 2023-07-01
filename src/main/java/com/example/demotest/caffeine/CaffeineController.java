package com.example.demotest.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caffeine")
public class CaffeineController {

    @Autowired
    private CaffeineService caffeineService;

    @GetMapping("/test")
    public ResponseEntity test(){
        System.out.println(caffeineService.findById(1L));
        System.out.println(caffeineService.saveProductInfo(2L));
        System.out.println(caffeineService.findById(2L));
        return ResponseEntity.ok().build();
    }
}
