package com.example.demotest.test;

import com.example.demotest.spi.TestInterfaceImpl1;

public class Test20 {
    public static void main(String[] args) {
        int x = 4;
        System.out.println("value is "+ ((x > 4) ? 99.9 :9));
        System.out.println(TestInterfaceImpl1.class.getDeclaredMethods().length);
        System.out.println(TestInterfaceImpl1.class.getMethods().length);
    }
}
