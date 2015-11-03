package com.edwise.pocs.jaxrswildcards.resource;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooResourceTest {

    private static final String ID_1234 = "ID_1234";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final int INTEGER_ID = 1234;
    private static final String DIRECTORIES_PATH = "/temp/files/another/mytext.txt";

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
        String result = fooResource.getFooById(ID_1234);

        assertThat(result).isEqualTo("Foo with id: " + ID_1234);
    }

    @Test
    public void testWhenGetFooWithNameAndSurnameThenReturnsNameAndSurname() {
        String result = fooResource.getFooWithNameAndSurname(NAME, SURNAME);

        assertThat(result).isEqualTo(NAME + " " + SURNAME);
    }

    @Test
    public void testWhenGetFooWithIntegerIdThenReturnsId() {
        String result = fooResource.getFooWithIntegerId(INTEGER_ID);

        assertThat(result).isEqualTo(Integer.toString(INTEGER_ID));
    }

    @Test
    public void testWhenGetFooWithWildcardThenReturnsPathVariable() {
        String result = fooResource.getFooWithWildcard(DIRECTORIES_PATH);

        assertThat(result).isEqualTo(DIRECTORIES_PATH);
    }
}