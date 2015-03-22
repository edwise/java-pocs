package com.edwise.example.passbyvalue;

public class PassByValueExample {

    public void primitiveExample() {
        int years = 10;
        System.out.println("Years before method: " + years);
        methodWithPrimitive(years);
        System.out.println("Years after method: " + years);
    }

    public void objectExample() {
        User user = new User("edwise");
        System.out.println("User before method: " + user);
        methodWithObjectSet(user);
        System.out.println("User after method: " + user);
    }

    public void otherObjectExample() {
        User user = new User("edwise");
        System.out.println("User before method: " + user);
        methodWithObjectNew(user);
        System.out.println("User after method: " + user);
    }

    private void methodWithPrimitive(int arg) {
        arg = arg + 3;
        System.out.println("Parameter modified: " + arg);
    }

    private void methodWithObjectSet(User arg) {
        arg.setName("newUser");
        System.out.println("Parameter modified: " + arg);
    }

    private void methodWithObjectNew(User arg) {
        arg = new User("newUser");
        System.out.println("Parameter modified: " + arg);
    }
}
