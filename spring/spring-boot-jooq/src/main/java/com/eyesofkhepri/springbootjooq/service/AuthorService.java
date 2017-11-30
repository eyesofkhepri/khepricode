package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.generate.tables.Author;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private DSLContext dsl;

    public void printAuthList() {
        Result<Record> authResult = dsl.select().from(Author.AUTHOR).fetch();

        for(Record auth : authResult) {
            int id = auth.getValue(Author.AUTHOR.ID);
            String firstName = auth.getValue(Author.AUTHOR.FIRST_NAME);
            String lastName = auth.getValue(Author.AUTHOR.LAST_NAME);

            System.out.println("Id = " + id + ", FiratName = " + firstName + ", LastName = " + lastName);
        }
    }


}
