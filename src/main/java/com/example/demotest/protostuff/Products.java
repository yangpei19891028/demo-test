package com.example.demotest.protostuff;

import lombok.Data;

import java.util.List;

@Data
public class Products {
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;

    private int i1;
    private int i2;
    private int i3;
    private int i4;
    private int i5;
    private int i6;
    private int i7;
    private int i8;
    private int i9;

    private boolean  b1;
    private boolean  b2;
    private boolean  b3;
    private boolean  b4;
    private boolean  b5;
    private boolean  b6;
    private boolean  b7;
    private boolean  b8;
    private boolean  b9;

    private List<String> list;

    public Products(){

    }

    public Products(String s1, String s2, String s3, String s4, String s5,
                    String s6, String s7, String s8, String s9, int i1, int i2, int i3,
                    int i4, int i5, int i6, int i7, int i8, int i9, boolean b1,
                    boolean b2, boolean b3, boolean b4, boolean b5, boolean b6,
                    boolean b7, boolean b8, boolean b9, List<String> list) {
        super();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.i6 = i6;
        this.i7 = i7;
        this.i8 = i8;
        this.i9 = i9;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.b9 = b9;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Products [s1=" + s1 + ", s2=" + s2 + ", s3=" + s3 + ", s4="
                + s4 + ", s5=" + s5 + ", s6=" + s6 + ", s7=" + s7 + ", s8="
                + s8 + ", s9=" + s9 + ", i1=" + i1 + ", i2=" + i2 + ", i3="
                + i3 + ", i4=" + i4 + ", i5=" + i5 + ", i6=" + i6 + ", i7="
                + i7 + ", i8=" + i8 + ", i9=" + i9 + ", b1=" + b1 + ", b2="
                + b2 + ", b3=" + b3 + ", b4=" + b4 + ", b5=" + b5 + ", b6="
                + b6 + ", b7=" + b7 + ", b8=" + b8 + ", b9=" + b9 + ", list="
                + list + "]";
    }
}
