package com.edwise.pocs.jooq;

import com.edwise.pocs.jooq.jdbc.DBConnector;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.maven.generated.Tables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        DSLContext jooqDSL = DSL.using(DBConnector.getInstance().connection(), SQLDialect.H2);


        Result<Record> result = jooqDSL.select().from(Tables.BOOK_CHARACTER).fetch();
        log.info("Resultado query: \n{}", result.toString());
    }
}
