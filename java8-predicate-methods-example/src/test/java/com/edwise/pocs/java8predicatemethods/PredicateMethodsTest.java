package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;
import com.edwise.pocs.java8predicatemethods.model.BookCharacter.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.edwise.pocs.java8predicatemethods.BookCharacterPredicate.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PredicateMethodsTest {

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
    public void testPredicateInlineYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords =
                bookCharacters.stream()
                              .filter(bChar -> bChar.getAge() < 90 && Weapon.SWORD.equals(bChar.getMainWeapon()))
                              .collect(Collectors.toList());

        assertThat(youngsAndSwords).extracting("name")
                                   .contains("Aragorn", "Boromir", "Sam")
                                   .doesNotContain("Gandalf", "Gimli", "Legolas", "Frodo");
    }

    @Test
    public void testPredicateInlineRefactorYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords =
                bookCharacters.stream()
                              .filter(bChar -> bChar.getAge() < 90)
                              .filter(bChar -> Weapon.SWORD.equals(bChar.getMainWeapon()))
                              .collect(Collectors.toList());

        assertThat(youngsAndSwords).extracting("name")
                                   .contains("Aragorn", "Boromir", "Sam")
                                   .doesNotContain("Gandalf", "Gimli", "Legolas", "Frodo");
    }

    @Test
    public void testPredicateDefinedInClassYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords =
                bookCharacters.stream()
                              .filter(isYoung().and(useSword()))
                              .collect(Collectors.toList());

        assertThat(youngsAndSwords).extracting("name")
                                   .contains("Aragorn", "Boromir", "Sam")
                                   .doesNotContain("Gandalf", "Gimli", "Legolas", "Frodo");
    }

    @Test
    public void testPredicateDefinedInClassIsNotHumanOrUseSword() {
        List<BookCharacter> notHumanOrSwords =
                bookCharacters.stream()
                              .filter(isHuman().negate().or(useSword()))
                              .collect(Collectors.toList());

        assertThat(notHumanOrSwords).extracting("name")
                                    .contains("Gandalf", "Aragorn", "Gimli", "Legolas", "Boromir", "Frodo", "Sam")
                                    .doesNotContain("Nothing...");
    }

    @Test
    public void testPredicateDefinedInClassNotUseSword() {
        List<BookCharacter> notUseSword =
                bookCharacters.stream()
                              .filter(useSword().negate())
                              .collect(Collectors.toList());

        assertThat(notUseSword).extracting("name")
                               .contains("Gandalf", "Gimli", "Legolas", "Frodo")
                               .doesNotContain("Aragorn", "Boromir", "Sam");
    }

    @Test
    public void testPredicateAsParameterYoungAndUseSword() {
        BookCharacter gandalf =
                new BookCharacter("Gandalf", Integer.MAX_VALUE, Weapon.STAFF, false);
        BookCharacterChecker bookCharacterChecker = new BookCharacterChecker();

        bookCharacterChecker.doSomeStuffIfThisAndValid(gandalf, bChar -> bChar.getAge() > 90);
    }

    @Test
    public void testPredicateEqualMethod() {
        BookCharacter aragorn = new BookCharacter("Aragorn", 88, Weapon.SWORD, true);
        Predicate<BookCharacter> equalToAragorn = Predicate.isEqual(aragorn);

        List<BookCharacter> allExceptAragorn =
                bookCharacters.stream()
                              .filter(equalToAragorn.negate())
                              .collect(Collectors.toList());

        assertThat(allExceptAragorn).doesNotContain(aragorn)
                                    .hasSize(6);
    }
}