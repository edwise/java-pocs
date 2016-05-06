package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;

import java.util.function.Predicate;

public class BookCharacterPredicate {
    public static Predicate<BookCharacter> isYoung() {
        return bChar -> bChar.getAge() < 70;
    }

    public static Predicate<BookCharacter> useSword() {
        return bChar -> BookCharacter.Weapon.SWORD.equals(bChar.getMainWeapon());
    }

    public static Predicate<BookCharacter> isHuman() {
        return BookCharacter::isHuman;
    }
}
