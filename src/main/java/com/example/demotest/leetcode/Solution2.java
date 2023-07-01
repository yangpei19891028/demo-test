package com.example.demotest.leetcode;

import lombok.Data;

public class Solution2 {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);

        Node prev = null;
        Node curr = n1;
        Node next = null;
        while (curr !=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr = n6;
        next = curr.getNext();
        while(next != null){
            System.out.println(next.getNum());
            next = next.getNext();
        }
    }
}

@Data
class Node{
    Integer num;
    Node next;
    public Node(Integer num){
        this.num = num;
    }

    public String toString(){
        return num.toString();
    }
}
