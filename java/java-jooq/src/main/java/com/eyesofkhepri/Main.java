package com.eyesofkhepri;

import com.eyesofkhepri.generate.tables.Author;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String userName = "jooq";
        String password = "pray";
        String url = "jdbc:mysql://127.0.0.1:3306/db_jooq";

        try(Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> result = create.select().from(Author.AUTHOR).fetch();

            for(Record r : result) {
                Integer id = r.getValue(Author.AUTHOR.ID);
                String firstName = r.getValue(Author.AUTHOR.FIRST_NAME);
                String lastName = r.getValue(Author.AUTHOR.LAST_NAME);
                System.out.println("ID : " + id + ", FirstName : " + firstName + ", LastName : " + lastName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
