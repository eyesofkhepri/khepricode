/*
 * This file is generated by jOOQ.
*/
package com.eyesofkhepri.generate;


import com.eyesofkhepri.generate.tables.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class DbJooq extends SchemaImpl {

    private static final long serialVersionUID = -176731137;

    /**
     * The reference instance of <code>db_jooq</code>
     */
    public static final DbJooq DB_JOOQ = new DbJooq();

    /**
     * The table <code>db_jooq.author</code>.
     */
    public final Author AUTHOR = com.eyesofkhepri.generate.tables.Author.AUTHOR;

    /**
     * No further instances allowed
     */
    private DbJooq() {
        super("db_jooq", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Author.AUTHOR);
    }
}
