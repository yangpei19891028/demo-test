package com.example.demotest.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("张三");
        list.set(0,"李四");
        list.remove(0);
        list.get(0);
        list.size();
    }
}
