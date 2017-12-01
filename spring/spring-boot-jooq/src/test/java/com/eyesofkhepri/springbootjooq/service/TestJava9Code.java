package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJava9Code {

    @Autowired
    DSLContext dsl;

    @After
    public void teardown() {
        dsl.delete(Book.BOOK).where(Book.BOOK.ID.gt(4)).execute();
    }


    @Test
    public void TestStreamCode() {
        dsl.select(Book.BOOK.TITLE)
                .from(Book.BOOK)
                .fetch();

    }
}
