package com.edwise.pocs.java8functionmethods;

import com.edwise.pocs.java8functionmethods.model.BookCharacter;
import com.edwise.pocs.java8functionmethods.model.BookCharacter.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FunctionMethodsTest {

    private List<BookCharacter> bookCharacters;

    @Before
    public void setUp() {
        bookCharacters = Arrays.asList(
                new BookCharacter("Gandalf", Integer.MAX_VALUE, Weapon.STAFF, false),
                new BookCharacter("Aragorn", 88, Weapon.SWORD, true),
                new BookCharacter("Gimli", 140, Weapon.AXE, false),
                new BookCharacter("Legolas", 2931, Weapon.BOW, false),
                new BookCharacter("Boromir", 41, Weapon.SWORD, true),
                new BookCharacter("Frodo", 51, Weapon.RING, false),
                new BookCharacter("Sam", 33, Weapon.SWORD, false)
        );
    }

    @Test
    public void testFunctionMethod() {
    }
}