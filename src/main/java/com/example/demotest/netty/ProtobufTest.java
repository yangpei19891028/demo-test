package com.example.demotest.netty;

import redis.clients.jedis.Jedis;

import java.io.*;

public class ProtobufTest {
    private static Jedis jedis = new Jedis("192.168.135.3");

    public static void main(String[] args) throws IOException {
        AuthenticateResponseProto.AuthenticateResponse.Builder builder = AuthenticateResponseProto.AuthenticateResponse.newBuilder();
        builder.setStatus(1);
        builder.setErrorCode(404);
        builder.setErrorMessage("failure");
        AuthenticateResponseProto.AuthenticateResponse authenticateResponse = builder.build();
        //序列化
        byte[] serializeBytes = authenticateResponse.toByteArray();
        jedis.set("1".getBytes(),serializeBytes);
//        System.out.println(serializeBytes.length);
//        System.out.println(AuthenticateResponseProto.AuthenticateResponse.parseFrom(serializeBytes));
//        //返序列化
        System.out.println(AuthenticateResponseProto.AuthenticateResponse.parseFrom(jedis.get("1".getBytes())));
    }
}
