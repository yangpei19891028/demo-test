package com.example.demotest.pattern;

import lombok.Data;

public class OptimizeBuilderDemo {
    public static void main(String[] args) {
        Product p = new ConcreteBuilder()
                .field1("1")
                .field2("2")
                .build();
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
        Builder field1(String value);
        Builder field2(String value);
        Product build();
    }

    static class ConcreteBuilder implements Builder{
        
        private Product p = new Product();

        @Override
        public Builder field1(String value) {
            System.out.println("校验field1");
            p.setField1(value);
            return this;
        }

        @Override
        public Builder field2(String value) {
            System.out.println("校验field2");
            p.setField2(value);
            return this;
        }

        @Override
        public Product build() {
            return p;
        }


    }
}
