package com.edwise.pocs.lombok.withoutlombok;

public class Book {

    private Long id;
    private String title;
    private String isbn;
    private Short type;

    public Book(Long id, String title, String isbn, Short type) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.type = type;
    }

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

    public static BookBuilder builder() {
         		return new BookBuilder();
         	}

         	public static class BookBuilder {
                private Long id;
                private String title;
                private String isbn;
                private Short type;

         		private BookBuilder() {}

         		public BookBuilder id(Long id) {
         			this.id = id;
         			return this;
         		}

         		public BookBuilder title(String title) {
         			this.title = title;
         			return this;
         		}

                public BookBuilder isbn(String isbn) {
                    this.isbn = isbn;
                    return this;
                }

                public BookBuilder type(Short type) {
                    this.type = type;
                    return this;
                }

         		@Override
                public String toString() {
         			return "BookBuilder(id = " + id
                            + ", title = " + title
                            + ", isbn = " + isbn
                            + ", type = " + type + ")";
         		}

         		public Book build() {
         			return new Book(id, title, isbn, type);
         		}
         	}
}
