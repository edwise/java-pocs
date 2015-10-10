package com.edwise.pocs.spymockito;

public class Bar extends Foo {

    public int myMethod() {
        System.out.println("Method to test!");
        String msg = this.doSomethingWithDatabase();
        System.out.println("Msg: ".concat(msg));

        return 1;
    }
}
