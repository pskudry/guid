package com.example.guid.dao;

import com.example.guid.dao.rel.EntitiesRel;
import com.example.guid.dao.rowmapper.EntitiesRowMapper;
import com.example.guid.model.Entities;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EntitiesRepository {

    private final DSLContext dslContext;

    public Entities getById(long id) {
        return dslContext.selectFrom(EntitiesRel.INSTANCE)
                .where(EntitiesRel.ID.eq(id))
                .fetchOne(new EntitiesRowMapper());
    }

    public Entities getByName(final String guidName) {
        return dslContext.selectFrom(EntitiesRel.INSTANCE)
                .where(EntitiesRel.NAME.eq(guidName))
                .fetchOne(new EntitiesRowMapper());
    }

    @Transactional
    public int deleteGuidByName(final String guidName) {
        return dslContext.deleteFrom(EntitiesRel.INSTANCE)
                .where(EntitiesRel.NAME.eq(guidName))
                .execute();
    }

    @Transactional
    public int addEntitie(final Entities entities) {
        return dslContext.insertInto(EntitiesRel.INSTANCE)
                .columns(EntitiesRel.NAME, EntitiesRel.DESCRIPTION)
                .values(entities.getName(), entities.getDescription())
                .execute();
    }

    @Transactional
    public int updateEntitie(final Entities entities) {
        return  dslContext.update(EntitiesRel.INSTANCE)
                .set(EntitiesRel.NAME, entities.getName())
                .set(EntitiesRel.DESCRIPTION, entities.getDescription())
                .where(EntitiesRel.ID.eq(entities.getId()))
                .execute();
    }
}
