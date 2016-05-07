package com.edwise.pocs.java8predicatemethods.model;

import java.util.Objects;

public class BookCharacter {
    private final String name;
    private final Integer age;
    private final Weapon mainWeapon;
    private final boolean human;

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

    public enum Weapon {SWORD, AXE, BOW, STAFF, RING}

    @Override
    public String toString() {
        return "BookCharacter{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCharacter that = (BookCharacter) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
