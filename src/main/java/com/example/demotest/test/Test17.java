package com.example.demotest.test;

import java.util.HashMap;
import java.util.Map;

public class Test17 {

    public static void main(String[] args) {
        /*while(true){
            System.out.println("22222");
            for(int i = 0 ; i < 5; i++){
                if(i == 2){
                    System.out.println("1111");
                    break;
                }
            }
        }*/
        Map<String,Long> m = new HashMap<>();
        Long a = m.get("5");
        Long b = 1L;
        System.out.println(b.equals(a));
    }
}
