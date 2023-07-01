package com.example.demotest.jdk.collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.set(1,"2_1");
        arrayList.remove("3");
        arrayList.remove(1);
        arrayList.add(0,"4");
        //arrayList.get(0);
    }
}
