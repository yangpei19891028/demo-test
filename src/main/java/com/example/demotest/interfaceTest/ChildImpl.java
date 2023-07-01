package com.example.demotest.interfaceTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ChildImpl implements ParentInterface {
    @Override
    public void test() {
        System.out.println(a);
    }

    public static void main(String[] args) {
        Class c = ParentInterface.class;
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
    }
}
