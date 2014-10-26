package com.edwise.pocs.lombok.withoutlombok;

public class Book {

    private Long id;
    private String title;
    private String isbn;
    private Short type;

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Short getType() {
        return type;
    }

    public Book setType(Short type) {
        this.type = type;
        return this;
    }

    // TODO implement builder
}
