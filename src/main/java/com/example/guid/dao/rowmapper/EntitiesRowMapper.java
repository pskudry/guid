package com.example.guid.dao.rowmapper;

import com.example.guid.dao.rel.EntitiesRel;
import com.example.guid.model.Entities;
import org.jooq.Record;
import org.jooq.RecordMapper;

public class EntitiesRowMapper implements RecordMapper<Record, Entities> {

    @Override
    public Entities map(Record record) {
        return new Entities()
                .setId(record.get(EntitiesRel.ID))
                .setName(record.get(EntitiesRel.NAME))
                .setDescription(record.get(EntitiesRel.DESCRIPTION));
    }
}
