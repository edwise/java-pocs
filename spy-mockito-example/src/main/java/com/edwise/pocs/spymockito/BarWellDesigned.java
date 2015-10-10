package com.edwise.pocs.spymockito;

public class BarWellDesigned {

    private Foo foo;

    public BarWellDesigned(Foo foo) {
        this.foo = foo;
    }

    public int myMethod() {
        System.out.println("Method to test!");
        String msg = foo.doSomethingWithDatabase();
        System.out.println("Msg: ".concat(msg));

        return 1;
    }
}
