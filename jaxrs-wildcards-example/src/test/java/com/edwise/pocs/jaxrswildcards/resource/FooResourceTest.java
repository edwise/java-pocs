package com.edwise.pocs.jaxrswildcards.resource;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooResourceTest {

    private FooResource fooResource;

    @Before
    public void setUp() {
        fooResource = new FooResource();
    }

    @Test
    public void testWhenGetAllFoosThenReturnsAllFoos() {
        String result = fooResource.getAllFoos();

        assertThat(result).isEqualTo("All foos");
    }

    @Test
    public void testWhenGetFooByIdThenReturnsFoo() {
        String result = fooResource.getFooById("ID_1234");

        assertThat(result).isEqualTo("Foo with id: " + "ID_1234");
    }

    @Test
    public void testWhenGetFooWithNameAndSurnameThenReturnsNameAndSurname() {
        String result = fooResource.getFooWithNameAndSurname("name", "surname");

        assertThat(result).isEqualTo("name" + " " + "surname");
    }

    @Test
    public void testWhenGetFooWithIntegerIdThenReturnsId() {
        String result = fooResource.getFooWithIntegerId(1234);

        assertThat(result).isEqualTo(Integer.toString(1234));
    }

    @Test
    public void testWhenGetFooWithWildcardThenReturnsPathVariable() {
        String result = fooResource.getFooWithWildcard("/temp/files/another/mytext.txt");

        assertThat(result).isEqualTo("/temp/files/another/mytext.txt");
    }
}