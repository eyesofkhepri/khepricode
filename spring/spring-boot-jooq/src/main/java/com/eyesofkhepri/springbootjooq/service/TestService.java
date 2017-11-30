package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Author;
import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import com.eyesofkhepri.springbootjooq.generate.tables.BookStore;
import com.eyesofkhepri.springbootjooq.generate.tables.BookToBookStore;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TestService {

    @Autowired
    private DSLContext dsl;

    public void testCode1() {
        String sql = dsl.select(Book.BOOK.TITLE, Author.AUTHOR.FIRST_NAME, Author.AUTHOR.LAST_NAME)
                .from(Book.BOOK)
                .join(Author.AUTHOR)
                .on(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.ID))
                .where(Book.BOOK.PUBLISHED_IN.eq(1948))
                .getSQL();

        log.info("testCode1 Sql = " + sql);

        Result<Record3<String, String, String>> result1 = dsl.select(Book.BOOK.TITLE, Author.AUTHOR.FIRST_NAME, Author.AUTHOR.LAST_NAME)
                .from(Book.BOOK)
                .join(Author.AUTHOR)
                .on(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.ID))
                .where(Book.BOOK.PUBLISHED_IN.eq(1948))
                .fetch();

        for(Record3<String, String ,String> v : result1) {
            log.info("##### Result1 #####");
            log.info("title = " + v.value1() + ", firstName = " + v.value2() + ", lastName = " + v.value3());
        }

        String sql2 = "SELECT title, first_name, last_name FROM book JOIN author ON book.author_id = author.id WHERE book.published_in = 1948";
        Result<Record> result2 = dsl.fetch(sql2);

        for(Record v : result2) {
            log.info("##### Result2 #####");
            log.info("title = " + v.getValue(Book.BOOK.TITLE) + ", firstName = " + v.getValue(Author.AUTHOR.FIRST_NAME) + ", lastName = " + v.getValue(Author.AUTHOR.LAST_NAME));
        }
    }



}
