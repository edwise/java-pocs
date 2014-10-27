package com.edwise.pocs.lombok.withlombok;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class UserTest {

    private static final long USER_ID_1234 = 1234l;
    private static final String USER_NAME_FRODO = "Frodo";
    private static final String USER_NAME_BILBO = "Bilbo";
    private static final String USER_SURNAME_BAGGINS = "Baggins";
    private static final int USER_TYPE_2 = 2;
    private static final LocalDate USER_BIRTHDATE_1500_10_23 = LocalDate.of(1500, 10, 23);
    private static final String USER_FAVORITE_BOOK_RED_BOOK = "Red Book";
    private static final String USER_FAVORITE_BOOK_THE_HOBBIT = "The Hobbit";
    private static final String USER_PHONE_NUMBER_666887766 = "666887766";
    private static final String USER_PHONE_NUMBER_666887765 = "666887765";

    @Test
    public void testUserLombokedSettersAndGetters() {
        User user = new User()
                .setId(USER_ID_1234)
                .setName(USER_NAME_FRODO)
                .setSurname(USER_SURNAME_BAGGINS)
                .setType(USER_TYPE_2)
                .setBirthDate(USER_BIRTHDATE_1500_10_23)
                .setFavoriteBooks(
                        Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT))
                .setPhoneNumber(USER_PHONE_NUMBER_666887766);

        assertThat(user.getId(), is(USER_ID_1234));
        assertThat(user.getName(), is(USER_NAME_FRODO));
        assertThat(user.getSurname(), is(USER_SURNAME_BAGGINS));
        assertThat(user.getType(), is(USER_TYPE_2));
        assertThat(user.getBirthDate(), is(USER_BIRTHDATE_1500_10_23));
        assertThat(user.getFavoriteBooks(),
                contains(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));
        assertThat(user.getPhoneNumber(), is(USER_PHONE_NUMBER_666887766));
    }

    @Test(expected = NullPointerException.class)
    public void testUserLombokedSetterNameWithNameNull() {
        User user = new User()
                .setName(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUserLombokedSetterSurNameWithSurNameNull() {
        User user = new User()
                .setSurname(null);
    }

    @Test
    public void testUserLombokedConstructorWithCorrectValues() {
        User user = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        assertThat(user.getId(), is(USER_ID_1234));
        assertThat(user.getName(), is(USER_NAME_FRODO));
        assertThat(user.getSurname(), is(USER_SURNAME_BAGGINS));
        assertThat(user.getType(), is(USER_TYPE_2));
        assertThat(user.getBirthDate(), is(USER_BIRTHDATE_1500_10_23));
        assertThat(user.getFavoriteBooks(),
                contains(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));
        assertThat(user.getPhoneNumber(), is(USER_PHONE_NUMBER_666887766));
    }

    @Test(expected = NullPointerException.class)
    public void testUserLombokedConstructorWithNameNull() {
        User user = new User(USER_ID_1234,
                null,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));
    }

    @Test(expected = NullPointerException.class)
    public void testUserLombokedConstructorWithSurNameNull() {
        User user = new User(USER_ID_1234,
                USER_NAME_FRODO,
                null,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));
    }

    @Test
    public void testUserLombokedToStringMethod() {
        User user = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        String userString = user.toString();

        assertThat(userString, containsString("id=" + USER_ID_1234));
        assertThat(userString, containsString("name=" + USER_NAME_FRODO));
        assertThat(userString, containsString("surname=" + USER_SURNAME_BAGGINS));
        assertThat(userString, containsString("type=" + USER_TYPE_2));
        assertThat(userString, containsString("birthDate=" + USER_BIRTHDATE_1500_10_23));
        assertThat(userString, containsString("phoneNumber=" + USER_PHONE_NUMBER_666887766));
        assertThat(userString, containsString("favoriteBooks="
                + Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT)));
    }

    @Test
    public void testUserLombokedEqualsMethod() {
        User user1 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        User user2 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));
    }

    @Test
    public void testUserLombokedEqualsMethodNotEqualUserLombokeds() {
        User user1 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        User user2 = new User(USER_ID_1234,
                USER_NAME_BILBO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887765,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK));

        assertFalse(user1.equals(user2));
        assertFalse(user2.equals(user1));
    }

    @Test
    public void testUserLombokedHashCodeMethod() {
        User user1 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        User user2 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testUserLombokedHashCodeMethodNotEquals() {
        User user1 = new User(USER_ID_1234,
                USER_NAME_FRODO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887766,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK, USER_FAVORITE_BOOK_THE_HOBBIT));

        User user2 = new User(USER_ID_1234,
                USER_NAME_BILBO,
                USER_SURNAME_BAGGINS,
                USER_TYPE_2,
                USER_BIRTHDATE_1500_10_23,
                USER_PHONE_NUMBER_666887765,
                Arrays.asList(USER_FAVORITE_BOOK_RED_BOOK));

        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}