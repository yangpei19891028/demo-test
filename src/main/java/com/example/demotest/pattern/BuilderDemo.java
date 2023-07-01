package com.example.demotest.pattern;

import lombok.Data;

public class BuilderDemo {

    public static void main(String[] args) {
        Product p = new Director(new ConcreteBuilder()).build("1","2");
        System.out.println(p);
    }

    @Data
    static class Product{
        String field1;
        String field2;
        @Override
        public String toString() {
            return "field1:" + field1 + ",field2:" + field2;
        }
    }

    interface Builder{
        void field1(String value);
        void field2(String value);
        Product create(String value1,String value2);
    }

    static class ConcreteBuilder implements Builder{

        private Product p = new Product();

        @Override
        public void field1(String value) {
            System.out.println("校验field1");
            p.setField1(value);
        }

        @Override
        public void field2(String value) {
            System.out.println("校验field2");
            p.setField2(value);
        }

        public Product create(String value1,String value2){
            return p;
        }
    }

    static class Director{
        static Builder builder;
        public Director(Builder builder){
            this.builder = builder;
        }

        public Product build(String value1,String value2){
            builder.field1(value1);
            builder.field2(value2);
            return builder.create(value1,value2);
        }
    }
}
