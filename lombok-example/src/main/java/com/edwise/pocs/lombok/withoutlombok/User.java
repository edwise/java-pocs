package com.edwise.pocs.lombok.withoutlombok;

import java.time.LocalDate;
import java.util.List;

public class User {

    private long id;
    private String name;
    private String surname;
    private int type;
    private LocalDate birthDate;
    private String phoneNumber;
    private List<String> favoriteBooks;

    public User() {
    }

    public User(long id, String name, String surname, int type,
                LocalDate birthDate, String phoneNumber,
                List<String> favoriteBooks) {
        if (name == null) {
            throw new NullPointerException("Name field can not be null!");
        }
        if (surname == null) {
            throw new NullPointerException("Surname field can not be null!");
        }

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.favoriteBooks = favoriteBooks;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        if (name == null) {
            throw new NullPointerException("Name field can not be null!");
        }
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        if (surname == null) {
            throw new NullPointerException("Surname field can not be null!");
        }
        this.surname = surname;
        return this;
    }

    public int getType() {
        return type;
    }

    public User setType(int type) {
        this.type = type;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public User setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return type == user.type &&
                !(birthDate != null ?
                        !birthDate.equals(user.birthDate) : user.birthDate != null) &&
                !(favoriteBooks != null ?
                        !favoriteBooks.equals(user.favoriteBooks) : user.favoriteBooks != null) &&
                name.equals(user.name) &&
                !(phoneNumber != null ?
                        !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) &&
                surname.equals(user.surname);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + type;
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (favoriteBooks != null ? favoriteBooks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name  +
                ", surname=" + surname  +
                ", type=" + type +
                ", birthDate=" + birthDate +
                ", phoneNumber=" + phoneNumber  +
                ", favoriteBooks=" + favoriteBooks +
                '}';
    }

}
