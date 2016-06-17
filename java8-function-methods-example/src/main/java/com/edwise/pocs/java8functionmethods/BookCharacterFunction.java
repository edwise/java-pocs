package com.edwise.pocs.java8functionmethods;

import com.edwise.pocs.java8functionmethods.model.BookCharacter;

import java.util.List;
import java.util.function.Function;

import static com.edwise.pocs.java8functionmethods.model.BookCharacter.Weapon;

public final class BookCharacterFunction {

    private BookCharacterFunction() {
    }

    public static Function<List<BookCharacter>, BookCharacter> findFirstSwordsman() {
        return list ->
                list.stream()
                    .filter(bookChar -> Weapon.SWORD.equals(bookChar.getMainWeapon()))
                    .findFirst()
                    .orElse(new BookCharacter("NOBODY", 0, Weapon.SWORD, true));
    }

    public static Function<BookCharacter, String> characterToCode() {
        return bookCharacter ->
                bookCharacter.getName() + "::" + bookCharacter.getMainWeapon() + "::" +
                        (bookCharacter.isHuman() ? "human" : "nonHuman");
    }
}
