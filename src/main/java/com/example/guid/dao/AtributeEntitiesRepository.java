package com.example.guid.dao;

import com.example.guid.dao.rel.AtributeEntitiesRel;
import com.example.guid.dao.rowmapper.AtributeEntitiesRowMapper;
import com.example.guid.model.AtributesEntities;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AtributeEntitiesRepository {

    private final DSLContext dslContext;

    public List<AtributesEntities> getById(long id) {
        return dslContext.selectFrom(AtributeEntitiesRel.INSTANCE)
                .where(AtributeEntitiesRel.ID_ENTITIES.eq(id))
                .fetch(new AtributeEntitiesRowMapper());
    }

    public AtributesEntities getByName(final String name) {
        return dslContext.selectFrom(AtributeEntitiesRel.INSTANCE)
                .where(AtributeEntitiesRel.NAME.eq(name))
                .fetchOne(new AtributeEntitiesRowMapper());
    }

    @Transactional
    public  int deleteByName(final String name) {
        return dslContext.deleteFrom(AtributeEntitiesRel.INSTANCE)
                .where(AtributeEntitiesRel.NAME.eq(name))
                .execute();
    }

    @Transactional
    public int deleteByIdEntities(long idEntities) {
        return dslContext.deleteFrom(AtributeEntitiesRel.INSTANCE)
                .where(AtributeEntitiesRel.ID_ENTITIES.eq(idEntities))
                .execute();
    }
    @Transactional
    public void addListAtributes(final List<AtributesEntities> atributesEntities) {
        dslContext.batch(atributesEntities.stream()
                .map(a-> dslContext
                        .insertInto(AtributeEntitiesRel.INSTANCE,
                                AtributeEntitiesRel.NAME,
                                AtributeEntitiesRel.ID_ENTITIES,
                                AtributeEntitiesRel.ID_TYPE)
                        .values(a.getName(), a.getIdEntities(), a.getIdType())
                ).collect(Collectors.toList())
        ).execute();
    }

    @Transactional
    public int addAtribute(final AtributesEntities atributesEntities) {
        return dslContext.insertInto(AtributeEntitiesRel.INSTANCE)
                .columns(
                AtributeEntitiesRel.NAME,
                AtributeEntitiesRel.ID_ENTITIES,
                AtributeEntitiesRel.ID_TYPE)
                .values(atributesEntities.getName(), atributesEntities.getIdEntities(), atributesEntities.getIdType())
                .execute();
    }

    @Transactional
    public int updateAtribute(final AtributesEntities atributesEntities) {
        return  dslContext.update(AtributeEntitiesRel.INSTANCE)
                .set(AtributeEntitiesRel.NAME, atributesEntities.getName())
                .set(AtributeEntitiesRel.ID_ENTITIES, atributesEntities.getIdEntities())
                .set(AtributeEntitiesRel.ID_TYPE, atributesEntities.getIdType())
                .where(AtributeEntitiesRel.ID.eq(atributesEntities.getId()))
                .execute();
    }
}
