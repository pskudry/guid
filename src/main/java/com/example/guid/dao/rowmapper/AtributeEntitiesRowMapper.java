package com.example.guid.dao.rowmapper;

import com.example.guid.dao.rel.AtributeEntitiesRel;
import com.example.guid.model.AtributesEntities;
import com.example.guid.model.AttributeType;
import org.jooq.Record;
import org.jooq.RecordMapper;

public class AtributeEntitiesRowMapper implements RecordMapper<Record, AtributesEntities> {

    @Override
    public AtributesEntities map(Record record) {
        return new AtributesEntities()
                .setId(record.get(AtributeEntitiesRel.ID))
                .setIdEntities(record.get(AtributeEntitiesRel.ID_ENTITIES))
                .setName(record.get(AtributeEntitiesRel.NAME))
                .setAttributeType(record.get(AtributeEntitiesRel.ATTR_TYPE, AttributeType.class));
    }
}
