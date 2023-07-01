package com.example.demotest.test;

import java.util.ArrayList;
import java.util.List;

public class Test16 {
    public static void main(String[] args) {
        List<Integer> sizeTable = new ArrayList<Integer>();
        for (int i = 512; i > 0; i <<= 1) { // lgtm[java/constant-comparison]
            sizeTable.add(i);
        }
        for(Integer integer : sizeTable){
            System.out.println(integer);
        }
    }
}
