package com.example.demotest.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Banana banana = new Banana();
        Fruit fruit = new Fruit();
        List<? extends Fruit> fruits = new ArrayList<Apple>();
        List<? extends Apple> apples = new ArrayList<>();

        List<String> l1 = new ArrayList<>();
        for(int i = 0 ;  i < 5; i++){
            l1.add(i+"");
        }

        List<String> l2 = new ArrayList<>();
        for(int i = 0 ;  i < 2; i++){
            l2.add(i+"");
        }
        l2.add("5");
        l1.retainAll(l2);
        System.out.println(l1.toString());
        System.out.println(l2.toString());

        List<String> list = Collections.EMPTY_LIST;
        list = Arrays.asList("awg","weg","wweg","wegwe");
        //list.forEach(System.out::println);
        list.forEach(str -> {
            if("awg".equals(str)){
                return;
            }
            System.out.println(str);
        });

        boolean flag = false;
        System.out.println(flag);

        System.out.println(2^3^3);

        int a = 1;
        int b = 2;
        int c = 3;
        a = a^b;
        b = a^b;
        a = a^b;
        a = a^c;
        c = a^c;
        a = a^c;
        System.out.println("a:" + a + "  b:" + b + "  c:" + c);
    }
}
