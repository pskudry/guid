package com.example.guid.dao.rowmapper;

import com.example.guid.dao.rel.AtributeTypeRel;
import com.example.guid.model.AtributeType;
import org.jooq.Record;
import org.jooq.RecordMapper;

public class AtributeTypeRowMapper implements RecordMapper<Record, AtributeType> {

    @Override
    public AtributeType map(Record record) {
        return new AtributeType()
                .setId(record.get(AtributeTypeRel.ID))
                .setName(record.get(AtributeTypeRel.NAME));
    }
}
