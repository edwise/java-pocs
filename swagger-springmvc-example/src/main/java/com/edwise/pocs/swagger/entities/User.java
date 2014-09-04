package com.edwise.pocs.swagger.entities;

import org.joda.time.LocalDate;

public class User {

    private Long id;
    private String name;
    private int type;
    private String phone;

    // TODO format?
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getType() {
        return type;
    }

    public User setType(int type) {
        this.type = type;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
