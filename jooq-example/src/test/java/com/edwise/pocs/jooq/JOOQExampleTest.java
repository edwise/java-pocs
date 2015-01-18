package com.edwise.pocs.jooq;

import com.edwise.pocs.jooq.jdbc.DBConnector;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.maven.generated.Tables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JOOQExampleTest {
    private static final Logger log = LoggerFactory.getLogger(JOOQExampleTest.class);

    private DSLContext jooqDSL;

    @Before
    public void setUp() {
        jooqDSL = DSL.using(DBConnector.getInstance().connection(), SQLDialect.H2);
    }

    @After
    public void tearDown() {
        DBConnector.getInstance().closeConnection();
    }

    @Test
    public void testSelectAll() {
        Result<Record> result = jooqDSL
                .select()
                .from(Tables.BOOK_CHARACTER)
                .fetch();

        log.info("Resultado query: \n{}", result.toString());
    }

    @Test
    public void testStreamForEachJava8() {
        jooqDSL.selectFrom(Tables.BOOK_CHARACTER)
                .where(Tables.BOOK_CHARACTER.ID.in(2, 3))
                .orderBy(Tables.BOOK_CHARACTER.NAME)
                .fetch()
                .stream()
                .map(bookCharacter -> bookCharacter.getName().toUpperCase())
                .forEach(s -> log.info("Name: {}", s));
    }
}