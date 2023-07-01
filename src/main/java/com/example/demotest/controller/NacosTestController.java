package com.example.demotest.controller;

//import com.samples.province.common.NacosFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/nacos")
public class NacosTestController {

//    private final RestTemplate restTemplate;
//
////    @Autowired
////    private NacosFeignClient nacosFeignClient;
//
//    @Autowired
//    private NacosFeignClient1 nacosFeignClient1;
//
//    @Autowired
//    public NacosTestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}
//
//    @GetMapping("/test")
//    public String test(){
//        //return restTemplate.getForObject("http://nacos-demo/test",String.class);
//       return nacosFeignClient1.test();
//       //return nacosFeignClient.test();
//    }
}
