package com.edwise.springbootseries.jooq;

import com.edwise.springbootseries.jooq.dao.BooksDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTest {

    @Autowired
    private BooksDao booksDao;

    @Test
    public void testApplication() {
        assertThat(booksDao, is(notNullValue()));
        assertThat(booksDao.testDslContext(), is(true));
    }
}