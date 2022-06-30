package com.example.guid.dao.rel;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.schema;

public final class AtributeEntitiesRel extends TableImpl<Record> {

    public static final AtributeEntitiesRel INSTANCE = new AtributeEntitiesRel();

    private AtributeEntitiesRel() {
        super(name("attributes_entities"), schema(name("guide")));
    }

    public static final TableField<Record, Long> ID = createField(name("id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, String> NAME = createField(name("attribute_name"), SQLDataType.VARCHAR, INSTANCE);

    public static final TableField<Record, Long> ID_ENTITIES = createField(name("entity_id"), SQLDataType.BIGINT, INSTANCE);

    public static final TableField<Record, Long> ID_TYPE = createField(name("type_id"), SQLDataType.BIGINT, INSTANCE);

}
