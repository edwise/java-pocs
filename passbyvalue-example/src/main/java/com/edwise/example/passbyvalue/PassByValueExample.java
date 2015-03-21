package com.edwise.example.passbyvalue;

public class PassByValueExample {

    public void primitiveExample() {
        int years = 10;
        methodWithPrimitive(years);
        System.out.println("Final years: " + years);
    }

    public void objectExample() {
        User user = new User("edu");
        methodWithObjectSet(user);
        System.out.println("Final user: " + user);
    }

    public void otherObjectExample() {
        User user2 = new User("edu");
        methodWithObjectNew(user2);
        System.out.println("Final user: " + user2);
    }

    private void methodWithPrimitive(int arg) {
        arg = arg + 3;
        System.out.println("Arg passed modified: " + arg);
    }

    private void methodWithObjectSet(User arg) {
        arg.setName("mayte");
        System.out.println("User passed modified: " + arg);
    }

    private void methodWithObjectNew(User arg) {
        arg = new User("mayte");
        System.out.println("User passed modified: " + arg);
    }
}
