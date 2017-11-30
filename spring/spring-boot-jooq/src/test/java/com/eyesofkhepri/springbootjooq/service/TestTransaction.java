package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import junit.framework.AssertionFailedError;
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
public class TestTransaction {

    @Autowired
    DSLContext dsl;

    @Autowired
    DataSourceTransactionManager txMgr;

    @Autowired
    BookService bookService;

    @After
    public void teardown() {
        dsl.delete(Book.BOOK).where(Book.BOOK.ID.gt(4)).execute();
    }

    @Test
    public void testExplicitTransactions() {
        boolean rollback = false;
        TransactionStatus tx = txMgr.getTransaction(new DefaultTransactionDefinition());

        try {
            for (int i = 0; i < 2; i++) {
                dsl.insertInto(Book.BOOK)
                        .set(Book.BOOK.ID, 5)
                        .set(Book.BOOK.AUTHOR_ID, 1)
                        .set(Book.BOOK.TITLE, "Book 5")
                        .set(Book.BOOK.PUBLISHED_IN, 2000)
                        .set(Book.BOOK.LANGUAGE_ID, 1)
                        .execute();
            }

            Assert.fail();
        } catch (Exception e) {
            txMgr.rollback(tx);
            rollback = true;
        }

        assertThat(4).isEqualTo(dsl.fetchCount(Book.BOOK));
        assertThat(rollback).isEqualTo(true);
    }

    @Test
    public void testDeclarativeTransactions() {
        boolean rollback = false;

        try {
            bookService.create(4, 1, "Book 4", 2000, 1);
        } catch(Exception e) {
            rollback = true;
        }

        assertThat(4).isEqualTo(dsl.fetchCount(Book.BOOK));
        assertThat(rollback).isEqualTo(true);
    }
}
