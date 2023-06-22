package org.example;

public class Main {

    public static void main(String[] args) {


        foo();
    }

    static void foo() {
        String m = "Hello";
        System.out.print(m);
        bar(m);
        System.out.print(m);
    }

    static void bar(String m) {
        m += " World!";
    }
}