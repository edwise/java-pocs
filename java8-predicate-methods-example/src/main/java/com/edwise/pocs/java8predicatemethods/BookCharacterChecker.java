package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;

import java.util.function.Predicate;

public class BookCharacterChecker {

    public boolean checkThisAndValid(BookCharacter bChar, Predicate<BookCharacter> predicate) {
        return predicate.and(BookCharacterPredicate.isValid()).test(bChar);
    }
}
