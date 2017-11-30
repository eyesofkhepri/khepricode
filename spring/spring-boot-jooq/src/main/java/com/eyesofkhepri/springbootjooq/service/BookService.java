package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Book;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private DSLContext dsl;

    @Transactional
    public void create(int id, int authorId, String title, int publishedIn, int languageId) {

         dsl.insertInto(Book.BOOK)
                 .set(Book.BOOK.ID, id)
                 .set(Book.BOOK.AUTHOR_ID, authorId)
                 .set(Book.BOOK.TITLE, title)
                 .set(Book.BOOK.PUBLISHED_IN, publishedIn)
                 .set(Book.BOOK.LANGUAGE_ID, languageId)
                 .execute();
    }
}
