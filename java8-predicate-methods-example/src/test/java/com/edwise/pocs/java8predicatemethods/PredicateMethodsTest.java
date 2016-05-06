package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;
import com.edwise.pocs.java8predicatemethods.model.BookCharacter.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.edwise.pocs.java8predicatemethods.BookCharacterPredicate.*;

public class PredicateMethodsTest {

    private List<BookCharacter> bookCharacters;

    @Before
    public void setUp() {
        bookCharacters = Arrays.asList(
                new BookCharacter("Gandalf", 500, Weapon.STAFF, false),
                new BookCharacter("Aragorn", 89, Weapon.SWORD, true),
                new BookCharacter("Gimli", 110, Weapon.AXE, false),
                new BookCharacter("Legolas", 350, Weapon.BOW, false),
                new BookCharacter("Boromir", 34, Weapon.SWORD, true),
                new BookCharacter("Frodo", 56, Weapon.SWORD, false),
                new BookCharacter("Sam", 63, Weapon.SWORD, false)
        );
    }

    @Test
    public void testPredicateInlineYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords = bookCharacters.stream()
                .filter(bChar -> bChar.getAge() < 70 && Weapon.SWORD.equals(bChar.getMainWeapon()))
                .collect(Collectors.toList());

        youngsAndSwords.forEach(System.out::println);
    }

    @Test
    public void testPredicateInlineRefactorYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords = bookCharacters.stream()
                .filter(bChar -> bChar.getAge() < 70)
                .filter(bChar -> Weapon.SWORD.equals(bChar.getMainWeapon()))
                .collect(Collectors.toList());

        youngsAndSwords.forEach(System.out::println);
    }

    @Test
    public void testPredicateDefinedInMethodsYoungAndUseSword() {
        List<BookCharacter> youngsAndSwords = bookCharacters.stream()
                .filter(isYoung().and(useSword()))
                .collect(Collectors.toList());

        youngsAndSwords.forEach(System.out::println);
    }

    @Test
    public void testPredicateDefinedInMethodsIsHumanOrUseSword() {
        List<BookCharacter> humanOrSwords = bookCharacters.stream()
                .filter(isHuman().or(useSword()))
                .collect(Collectors.toList());

        humanOrSwords.forEach(System.out::println);
    }

    @Test
    public void testPredicateDefinedInMethodsNotUseSword() {
        List<BookCharacter> notUseSword = bookCharacters.stream()
                .filter(useSword().negate())
                .collect(Collectors.toList());

        notUseSword.forEach(System.out::println);
    }

    // TODO picar un método que reciba Predicate, y dentro lo use para negate, and, or...?
}