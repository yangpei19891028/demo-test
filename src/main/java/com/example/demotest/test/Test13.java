package com.example.demotest.test;

public class Test13 {
    public static void main(String[] args) {
        try {
            try {
                try {
                    int a = 1/0;
                } catch (RuntimeException x) {
                    System.out.println("5");
                    throw x;
                } finally {
                    System.out.println("1");
                }
            } finally {
                System.out.println("2");
            }
            System.out.println("3");
        } finally {
            System.out.println("4");
        }
    }
}
