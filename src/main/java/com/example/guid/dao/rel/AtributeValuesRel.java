package com.example.guid.dao.rel;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.schema;

public final class AtributeValuesRel extends TableImpl<Record> {
    public static final AtributeValuesRel INSTANCE = new AtributeValuesRel();

    private AtributeValuesRel() {
        super(name("attribute_values"), schema(name("guide")));
    }

    public static final TableField<Record, Long> ID = createField(name("id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, String> VALUE = createField(name("value"), SQLDataType.VARCHAR, INSTANCE);

    public static final TableField<Record, Long> RECORD_ID = createField(name("record_id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, Long> ID_ATRIBUTE = createField(name("attr_id"), SQLDataType.BIGINT, INSTANCE);

}
