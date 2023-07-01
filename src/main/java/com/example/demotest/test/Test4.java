package com.example.demotest.test;

public class Test4 {
    public static void main(String[] args) {
        long l = 123456;
        double f = 123.456F;
        l = (long) f;
        f = l;
        System.out.println(l);
        System.out.println(f);
    }
}
