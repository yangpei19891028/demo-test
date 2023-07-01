package com.example.demotest.protostuff;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;

public class ProtostuffTest {
    public static void main(String[] args) {
        Products p1 = new Products();
        Products p2 = new Products();
        List<Products> pList = new ArrayList<>();
//        pList.add(p1);
//        pList.add(p2);
        List<byte[]> serialize = serializeProtoStuffProductsList(pList);
        System.out.println(deserializeProtoStuffDataListToProductsList(serialize));
    }

    public static List<byte[]> serializeProtoStuffProductsList(List<Products> pList) {
        if(pList == null  ||  pList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis() ;
        List<byte[]> bytes = new ArrayList<byte[]>();
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        for(Products p : pList) {
            try {
//                protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
                bytes.add(protostuff);
            } finally {
                buffer.clear();
            }
        }
        long end = System.currentTimeMillis() ;
        return bytes;
    }

    public static List<Products> deserializeProtoStuffDataListToProductsList(
            List<byte[]> bytesList) {
        if(bytesList == null || bytesList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis() ;
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        List<Products> list = new ArrayList<Products>();
        for(byte[] bs : bytesList) {
            Products product = new Products();
//            ProtostuffIOUtil.mergeFrom(bs, product, schema);
//            list.add(product);
        }
        long end = System.currentTimeMillis() ;
        return list;
    }
}
