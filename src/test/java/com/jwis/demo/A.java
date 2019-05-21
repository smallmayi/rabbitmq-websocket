package com.jwis.demo;

import com.alibaba.fastjson.JSONArray;

public class A {
    int a = 5;
    static int b =10;


    public static void main(String[] args) {
        System.out.println(new A().a);
        System.out.println(A.b);
        new A().add();
        new A().add();
        System.out.println(new A().a);
        System.out.println(A.b);

    }

    void add(){
        a = a+1;
        b = b+1;
        System.out.println("----");
    }
}
