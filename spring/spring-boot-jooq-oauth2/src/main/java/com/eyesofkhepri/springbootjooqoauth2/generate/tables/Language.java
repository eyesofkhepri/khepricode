/*
 * This file is generated by jOOQ.
*/
package com.eyesofkhepri.springbootjooqoauth2.generate.tables;


import com.eyesofkhepri.springbootjooqoauth2.generate.DbJooq;
import com.eyesofkhepri.springbootjooqoauth2.generate.Indexes;
import com.eyesofkhepri.springbootjooqoauth2.generate.Keys;
import com.eyesofkhepri.springbootjooqoauth2.generate.tables.records.LanguageRecord;

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
public class Language extends TableImpl<LanguageRecord> {

    private static final long serialVersionUID = -324795356;

    /**
     * The reference instance of <code>db_jooq.language</code>
     */
    public static final Language LANGUAGE = new Language();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LanguageRecord> getRecordType() {
        return LanguageRecord.class;
    }

    /**
     * The column <code>db_jooq.language.id</code>.
     */
    public final TableField<LanguageRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>db_jooq.language.cd</code>.
     */
    public final TableField<LanguageRecord, String> CD = createField("cd", org.jooq.impl.SQLDataType.VARCHAR(2).nullable(false), this, "");

    /**
     * The column <code>db_jooq.language.description</code>.
     */
    public final TableField<LanguageRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * Create a <code>db_jooq.language</code> table reference
     */
    public Language() {
        this(DSL.name("language"), null);
    }

    /**
     * Create an aliased <code>db_jooq.language</code> table reference
     */
    public Language(String alias) {
        this(DSL.name(alias), LANGUAGE);
    }

    /**
     * Create an aliased <code>db_jooq.language</code> table reference
     */
    public Language(Name alias) {
        this(alias, LANGUAGE);
    }

    private Language(Name alias, Table<LanguageRecord> aliased) {
        this(alias, aliased, null);
    }

    private Language(Name alias, Table<LanguageRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.LANGUAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<LanguageRecord> getPrimaryKey() {
        return Keys.KEY_LANGUAGE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LanguageRecord>> getKeys() {
        return Arrays.<UniqueKey<LanguageRecord>>asList(Keys.KEY_LANGUAGE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Language as(String alias) {
        return new Language(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Language as(Name alias) {
        return new Language(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Language rename(String name) {
        return new Language(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Language rename(Name name) {
        return new Language(name, null);
    }
}
