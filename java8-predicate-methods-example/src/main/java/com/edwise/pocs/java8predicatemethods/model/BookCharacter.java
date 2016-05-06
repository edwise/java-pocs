package com.edwise.pocs.java8predicatemethods.model;

public class BookCharacter {
    private String name;
    private Integer age;
    private Weapon mainWeapon;
    private boolean human;

    public BookCharacter(String name, Integer age, Weapon mainWeapon, boolean human) {
        this.name = name;
        this.age = age;
        this.mainWeapon = mainWeapon;
        this.human = human;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Weapon getMainWeapon() {
        return mainWeapon;
    }

    public boolean isHuman() {
        return human;
    }

    public enum Weapon {SWORD, AXE, BOW, STAFF}

    @Override
    public String toString() {
        return "BookCharacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
