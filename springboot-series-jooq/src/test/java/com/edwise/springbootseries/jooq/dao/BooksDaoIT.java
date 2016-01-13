package com.edwise.springbootseries.jooq.dao;

import com.edwise.springbootseries.jooq.Application;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.jooq.domain.Tables;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class BooksDaoIT {
    private static final Logger LOG = LoggerFactory.getLogger(BooksDaoIT.class);

    private static final int EXISTING_CHAR_ID = 4;
    private static final int NOT_EXISTING_CHAR_ID = 13;

    @Autowired
    private BooksDao booksDao;

    @Test
    public void getAllBookCharactersShouldReturnAllTheChars() {
        Result<Record> allBookCharacters = booksDao.getAllBookCharacters();

        allBookCharacters.stream()
                .map(record -> record.getValue(Tables.BOOK_CHARACTER.NAME))
                .forEach(name -> LOG.info("Char: {}", name));

        assertThat(allBookCharacters)
                .extracting(record -> record.getValue(Tables.BOOK_CHARACTER.NAME))
                .contains("Samwise", "Gandalf", "Frodo", "Saruman", "Aragorn");
    }

    @Test
    public void getAllBookCharactersOrderByNameShouldReturnCharsSorted() {
        Result<Record> allBookCharactersByName = booksDao.getAllBookCharactersOrderByName();

        assertThat(allBookCharactersByName)
                .extracting(record -> record.getValue(Tables.BOOK_CHARACTER.NAME))
                .isSorted();
    }

    @Test
    public void getBookByIdThatExistsShouldReturnValidRecord() {
        Optional<Record> bookCharacter = booksDao.getBookCharacterById(EXISTING_CHAR_ID);

        assertThat(
                bookCharacter
                        .orElseThrow(() -> new AssertionError("Not existing character"))
                        .getValue(Tables.BOOK_CHARACTER.NAME))
                .isEqualTo("Samwise");
    }

    @Test
    public void getBookByIdThatNotExistsShouldReturnEmptyOptionalRecord() {
        Optional<Record> bookCharacter = booksDao.getBookCharacterById(NOT_EXISTING_CHAR_ID);

        assertThat(bookCharacter.isPresent()).isFalse();
    }
}