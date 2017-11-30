package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Author;
import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import com.eyesofkhepri.springbootjooq.generate.tables.BookStore;
import com.eyesofkhepri.springbootjooq.generate.tables.BookToBookStore;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.jooq.impl.DSL.countDistinct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJoin {

    @Autowired
    DSLContext dsl;

    @Test
    public void TestCode1() throws Exception{
        Book b = Book.BOOK.as("b");
        Author a = Author.AUTHOR.as("a");
        BookStore s = BookStore.BOOK_STORE.as("s");
        BookToBookStore t = BookToBookStore.BOOK_TO_BOOK_STORE.as("t");

        Result<Record3<String, String, Integer>> result =
                dsl.select(a.FIRST_NAME, a.LAST_NAME, countDistinct(s.NAME))
                        .from(a)
                        .join(b).on(b.AUTHOR_ID.eq(a.ID))
                        .join(t).on(t.BOOK_ID.eq(b.ID))
                        .join(s).on(t.NAME.eq(s.NAME))
                        .groupBy(a.FIRST_NAME, a.LAST_NAME)
                        .orderBy(countDistinct(s.NAME).desc())
                        .fetch();

       assertThat(2).isEqualTo(result.size());
       assertThat("Paulo").isEqualTo(result.getValue(0, a.FIRST_NAME));
    }
}
