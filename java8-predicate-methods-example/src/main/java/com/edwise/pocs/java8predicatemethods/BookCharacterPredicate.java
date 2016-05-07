package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;

import java.util.function.Predicate;

public class BookCharacterPredicate {
    public static Predicate<BookCharacter> isYoung() {
        return bChar -> bChar.getAge() < 90;
    }

    public static Predicate<BookCharacter> useSword() {
        return bChar -> BookCharacter.Weapon.SWORD.equals(bChar.getMainWeapon());
    }

    public static Predicate<BookCharacter> isHuman() {
        return BookCharacter::isHuman;
    }

    public static Predicate<BookCharacter> isValid() {
        return bChar -> bChar.getName() != null &&
                bChar.getAge() > 0 &&
                bChar.getMainWeapon() != null;
    }
}
