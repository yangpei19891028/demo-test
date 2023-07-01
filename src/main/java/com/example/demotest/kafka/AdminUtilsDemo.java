package com.example.demotest.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import kafka.zk.AdminZkClient;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Properties;

public class AdminUtilsDemo {
    public static void main(String[] args) {
        ZkUtils zkUtils = ZkUtils.apply("192.168.135.3:2181",30000,30000, JaasUtils.isZkSecurityEnabled());
        AdminUtils.createTopic(zkUtils,"test01",3,2,new Properties(), RackAwareMode.Enforced$.MODULE$);
        //AdminUtils.deleteTopic(zkUtils,"test01");
        //new AdminZkClient().deleteTopic();
    }
}
