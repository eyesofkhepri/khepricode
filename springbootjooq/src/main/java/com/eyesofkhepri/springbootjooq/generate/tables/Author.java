/*
 * This file is generated by jOOQ.
*/
package com.eyesofkhepri.springbootjooq.generate.tables;


import com.eyesofkhepri.springbootjooq.generate.DbJooq;
import com.eyesofkhepri.springbootjooq.generate.Indexes;
import com.eyesofkhepri.springbootjooq.generate.Keys;
import com.eyesofkhepri.springbootjooq.generate.tables.records.AuthorRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class Author extends TableImpl<AuthorRecord> {

    private static final long serialVersionUID = -1665612580;

    /**
     * The reference instance of <code>db_jooq.author</code>
     */
    public static final Author AUTHOR = new Author();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorRecord> getRecordType() {
        return AuthorRecord.class;
    }

    /**
     * The column <code>db_jooq.author.id</code>.
     */
    public final TableField<AuthorRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>db_jooq.author.first_name</code>.
     */
    public final TableField<AuthorRecord, String> FIRST_NAME = createField("first_name", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>db_jooq.author.last_name</code>.
     */
    public final TableField<AuthorRecord, String> LAST_NAME = createField("last_name", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>db_jooq.author</code> table reference
     */
    public Author() {
        this(DSL.name("author"), null);
    }

    /**
     * Create an aliased <code>db_jooq.author</code> table reference
     */
    public Author(String alias) {
        this(DSL.name(alias), AUTHOR);
    }

    /**
     * Create an aliased <code>db_jooq.author</code> table reference
     */
    public Author(Name alias) {
        this(alias, AUTHOR);
    }

    private Author(Name alias, Table<AuthorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Author(Name alias, Table<AuthorRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.AUTHOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AuthorRecord> getPrimaryKey() {
        return Keys.KEY_AUTHOR_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AuthorRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthorRecord>>asList(Keys.KEY_AUTHOR_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author as(String alias) {
        return new Author(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author as(Name alias) {
        return new Author(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Author rename(String name) {
        return new Author(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Author rename(Name name) {
        return new Author(name, null);
    }
}
