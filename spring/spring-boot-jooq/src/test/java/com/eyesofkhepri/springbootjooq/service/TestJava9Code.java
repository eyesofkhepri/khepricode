package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.domain.BookModel;
import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.util.xml.jaxb.Column;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJava9Code {

    @Autowired
    DSLContext dsl;

    @Test
    public void TestLamdaCode() {
        dsl.select(Book.BOOK.AUTHOR_ID, Book.BOOK.TITLE)
                .from(Book.BOOK)
                .fetch()
        .map(rs -> new BookModel(rs.getValue(Book.BOOK.AUTHOR_ID), rs.getValue(Book.BOOK.TITLE)))
        .forEach(System.out::println);

    }

    @Test
    public void TestStreamCode() {
        Map<Integer, List<BookModel>> l = dsl.select(Book.BOOK.AUTHOR_ID, Book.BOOK.TITLE)
            .from(Book.BOOK)
            .fetch()
            .stream()
            .collect(Collectors.groupingBy(
                    r -> r.getValue(Book.BOOK.AUTHOR_ID),
                    Collectors.mapping(r -> new BookModel(r.getValue(Book.BOOK.AUTHOR_ID), r.getValue(Book.BOOK.TITLE)), Collectors.toList())
            ));

            l.forEach((authorId, v) -> {
                System.out.println("AUTHORID = " + authorId + ", Data List = " + v);
            });
                ;

    }
}
