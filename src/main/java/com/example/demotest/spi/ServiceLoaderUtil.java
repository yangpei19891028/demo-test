package com.example.demotest.spi;

import java.util.ServiceLoader;

public class ServiceLoaderUtil {

    public static <S> ServiceLoader<S> getServiceLoader(Class<S> clazz) {
        return ServiceLoader.load(clazz, clazz.getClassLoader());
    }


    public static void main(String[] args) {
        ServiceLoader<TestInterface> serviceLoader = com.alibaba.csp.sentinel.spi.ServiceLoaderUtil.getServiceLoader(TestInterface.class);
        for (TestInterface spi : serviceLoader) {
            spi.upload();
        }
    }
}
