package com.example.guid.dao.rel;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.schema;

public final class EntitiesRel extends TableImpl<Record> {

    public static final EntitiesRel  INSTANCE = new EntitiesRel();

    private EntitiesRel() {
        super(name("entities"), schema(name("guide")));
    }

    public static final TableField<Record, Long> ID = createField(name("id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, String> NAME = createField(name("entity_name"), SQLDataType.VARCHAR, INSTANCE);

    public static final TableField<Record, String> DESCRIPTION = createField(name("description"), SQLDataType.VARCHAR, INSTANCE);
}
