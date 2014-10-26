package com.edwise.pocs.lombok.withlombok;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    @Test
    public void testBuilderBook() {
        Book book = Book.builder()
                .id(12L)
                .title("The Hobbit")
                .type((short) 2)
                .isbn("111-232323-4544")
                .build();

        assertThat(book.getId(), is(12L));
        assertThat(book.getTitle(), is("The Hobbit"));
        assertThat(book.getType(), is((short) 2));
        assertThat(book.getIsbn(), is("111-232323-4544"));
    }
}