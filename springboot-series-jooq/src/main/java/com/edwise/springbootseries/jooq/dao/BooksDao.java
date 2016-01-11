package com.edwise.springbootseries.jooq.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BooksDao {

    private DSLContext create;

    @Autowired
    public BooksDao(DSLContext dslContext) {
        this.create = dslContext;
    }


    public boolean testDslContext() {
        return create != null;
    }
}
