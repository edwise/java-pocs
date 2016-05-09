package com.edwise.pocs.java8predicatemethods;

import com.edwise.pocs.java8predicatemethods.model.BookCharacter;

import java.util.function.Predicate;

public class BookCharacterChecker {

    public void doSomeStuffIfThisAndValid(BookCharacter bChar,
                                          Predicate<BookCharacter> predicate) {
        if (predicate.and(BookCharacterPredicate.isValid()).test(bChar)) {
            // do some stuff
            System.out.println("doing stuff with result true");
        } else {
            // do other stuff
            System.out.println("doing stuff with result false");
        }
    }
}
