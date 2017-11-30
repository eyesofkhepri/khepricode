/*
 * This file is generated by jOOQ.
*/
package com.eyesofkhepri.springbootjooq.generate.tables;


import com.eyesofkhepri.springbootjooq.generate.DbJooq;
import com.eyesofkhepri.springbootjooq.generate.Indexes;
import com.eyesofkhepri.springbootjooq.generate.Keys;
import com.eyesofkhepri.springbootjooq.generate.tables.records.BookRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Book extends TableImpl<BookRecord> {

    private static final long serialVersionUID = -306707217;

    /**
     * The reference instance of <code>db_jooq.book</code>
     */
    public static final Book BOOK = new Book();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookRecord> getRecordType() {
        return BookRecord.class;
    }

    /**
     * The column <code>db_jooq.book.id</code>.
     */
    public final TableField<BookRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>db_jooq.book.author_id</code>.
     */
    public final TableField<BookRecord, Integer> AUTHOR_ID = createField("author_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>db_jooq.book.title</code>.
     */
    public final TableField<BookRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(400).nullable(false), this, "");

    /**
     * The column <code>db_jooq.book.published_in</code>.
     */
    public final TableField<BookRecord, Integer> PUBLISHED_IN = createField("published_in", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>db_jooq.book.language_id</code>.
     */
    public final TableField<BookRecord, Integer> LANGUAGE_ID = createField("language_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>db_jooq.book</code> table reference
     */
    public Book() {
        this(DSL.name("book"), null);
    }

    /**
     * Create an aliased <code>db_jooq.book</code> table reference
     */
    public Book(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>db_jooq.book</code> table reference
     */
    public Book(Name alias) {
        this(alias, BOOK);
    }

    private Book(Name alias, Table<BookRecord> aliased) {
        this(alias, aliased, null);
    }

    private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DbJooq.DB_JOOQ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.BOOK_FK_BOOK_AUTHOR, Indexes.BOOK_FK_BOOK_LANGUAGE, Indexes.BOOK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BookRecord> getPrimaryKey() {
        return Keys.KEY_BOOK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BookRecord>> getKeys() {
        return Arrays.<UniqueKey<BookRecord>>asList(Keys.KEY_BOOK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<BookRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BookRecord, ?>>asList(Keys.FK_BOOK_AUTHOR, Keys.FK_BOOK_LANGUAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book as(String alias) {
        return new Book(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book as(Name alias) {
        return new Book(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(String name) {
        return new Book(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Name name) {
        return new Book(name, null);
    }
}
