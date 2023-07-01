package com.example.demotest.jdk.collection;

import java.util.LinkedList;

public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst("first");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add(2,"3_1");
        System.out.println(linkedList.toString());
        linkedList.remove();
        System.out.println(linkedList.toString());
        linkedList.remove("2");
        System.out.println(linkedList.toString());
        linkedList.remove(1);
        System.out.println(linkedList.toString());
        linkedList.set(1,"1_1");
        System.out.println(linkedList.toString());
        linkedList.addLast("last");

        linkedList.get(2);
        linkedList.getFirst();
        linkedList.peekFirst();
        linkedList.removeLast();
        linkedList.peek();
    }
}
