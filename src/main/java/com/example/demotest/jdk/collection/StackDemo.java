package com.example.demotest.jdk.collection;

import java.util.Stack;

public class StackDemo {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("张三");
        stack.add("李四");
        stack.add("王五");
        stack.push("赵六");
        System.out.println(stack.pop());
        stack.remove(1);
    }
}
