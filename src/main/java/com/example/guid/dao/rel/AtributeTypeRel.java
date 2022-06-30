package com.example.guid.dao.rel;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.schema;

public final class AtributeTypeRel extends TableImpl<Record> {

    public static final AtributeTypeRel INSTANCE = new AtributeTypeRel();

    private AtributeTypeRel() {
        super(name("attributes_type"), schema(name("guide")));
    }

    public static final TableField<Record, Long> ID = createField(name("id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, String> NAME = createField(name("type_name"), SQLDataType.VARCHAR, INSTANCE);

}
