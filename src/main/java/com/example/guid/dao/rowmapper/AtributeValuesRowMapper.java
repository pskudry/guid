package com.example.guid.dao.rowmapper;

import com.example.guid.dao.rel.AtributeValuesRel;
import com.example.guid.model.AtributesValue;
import org.jooq.Record;
import org.jooq.RecordMapper;

public class AtributeValuesRowMapper implements RecordMapper<Record, AtributesValue> {

    @Override
    public  AtributesValue map(Record record) {
        return new AtributesValue()
                .setId(record.get(AtributeValuesRel.ID))
                .setIdAtribut(record.get(AtributeValuesRel.ID_ATRIBUTE))
                .setNumberRecord(record.get(AtributeValuesRel.RECORD_ID))
                .setValues(record.get(AtributeValuesRel.VALUE));
    }
}
