package com.example.guid.dao;

import com.example.guid.dao.rel.AtributeValuesRel;
import com.example.guid.dao.rowmapper.AtributeValuesRowMapper;
import com.example.guid.model.AtributesValue;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtributeValuesRepository {

    private final DSLContext dslContext;

    @Transactional
    public int deleteByIdAtribute(long idAtribute) {
        return dslContext.deleteFrom(AtributeValuesRel.INSTANCE)
                .where(AtributeValuesRel.ID_ATRIBUTE.eq(idAtribute))
                .execute();
    }

    @Transactional
    public int addValue(final AtributesValue atributesValue) {
        return dslContext.insertInto(AtributeValuesRel.INSTANCE)
                .columns(AtributeValuesRel.ID_ATRIBUTE, AtributeValuesRel.RECORD_ID, AtributeValuesRel.VALUE)
                .values(atributesValue.getIdAtribut(), atributesValue.getNumberRecord(), atributesValue.getValues())
                .execute();
    }

    @Transactional
    public int updateValue(final AtributesValue atributesValue) {
        return dslContext.update(AtributeValuesRel.INSTANCE)
                .set(AtributeValuesRel.ID_ATRIBUTE, atributesValue.getIdAtribut())
                .set(AtributeValuesRel.RECORD_ID, atributesValue.getNumberRecord())
                .set(AtributeValuesRel.VALUE, atributesValue.getValues())
                .execute();
    }

    public AtributesValue selectValue(final String value) {
        return  dslContext.selectFrom(AtributeValuesRel.INSTANCE)
                .where(AtributeValuesRel.VALUE.eq(value))
                .fetchOne(new AtributeValuesRowMapper());
    }

    public List<AtributesValue> selectListValue(List<Long> idAtr) {
        return dslContext.selectFrom(AtributeValuesRel.INSTANCE)
                .where(AtributeValuesRel.ID_ATRIBUTE.in(idAtr))
                .fetch(new AtributeValuesRowMapper());
    }
    public AtributesValue selectByAtrId(Long atrId) {
        return  dslContext.selectFrom(AtributeValuesRel.INSTANCE)
                .where(AtributeValuesRel.ID_ATRIBUTE.eq(atrId))
                .fetchOne(new AtributeValuesRowMapper());
    }
}
