package com.edwise.pocs.java8functionmethods;

import com.edwise.pocs.java8functionmethods.model.BookCharacter;
import com.edwise.pocs.java8functionmethods.model.BookCharacter.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.edwise.pocs.java8functionmethods.BookCharacterFunction.characterToCode;
import static com.edwise.pocs.java8functionmethods.BookCharacterFunction.findFirstSwordsman;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

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
    public void testFunctionAndThenMethod() {
        Function<List<BookCharacter>, String> andThenFunction =
                findFirstSwordsman().andThen(characterToCode());

        assertThat(andThenFunction.apply(bookCharacters))
                .isEqualTo("Aragorn::SWORD::human");
    }

    @Test
    public void testFunctionComposeMethod() {
        Function<List<BookCharacter>, String> composeFunction =
                characterToCode().compose(findFirstSwordsman());

        assertThat(composeFunction.apply(bookCharacters))
                .isEqualTo("Aragorn::SWORD::human");
    }

    @Test
    public void testWithNoFunctionIdentityMethod() {
        Map<String, BookCharacter> characterMap =
                bookCharacters.stream()
                              .collect(Collectors.toMap(BookCharacter::getName,
                                      bookChar -> bookChar));

        assertThatMapContainsListCharacters(characterMap);
    }

    @Test
    public void testWithFunctionIdentityMethod() {
        Map<String, BookCharacter> characterMap =
                bookCharacters.stream()
                              .collect(Collectors.toMap(BookCharacter::getName,
                                      Function.identity()));

        assertThatMapContainsListCharacters(characterMap);
    }

    private void assertThatMapContainsListCharacters(Map<String, BookCharacter> characterMap) {
        assertThat(characterMap).hasSize(bookCharacters.size())
                                .containsOnly(entry("Gandalf", bookCharacters.get(0)),
                                        entry("Aragorn", bookCharacters.get(1)),
                                        entry("Gimli", bookCharacters.get(2)),
                                        entry("Legolas", bookCharacters.get(3)),
                                        entry("Boromir", bookCharacters.get(4)),
                                        entry("Frodo", bookCharacters.get(5)),
                                        entry("Sam", bookCharacters.get(6)));
    }
}