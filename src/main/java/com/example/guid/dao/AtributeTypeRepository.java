package com.example.guid.dao;

import com.example.guid.dao.rel.AtributeTypeRel;
import com.example.guid.dao.rowmapper.AtributeTypeRowMapper;
import com.example.guid.model.AtributeType;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtributeTypeRepository {

    private final DSLContext dslContext;

    public List<AtributeType> getAllType() {
        return dslContext.selectFrom(AtributeTypeRel.INSTANCE)
                .fetch(new AtributeTypeRowMapper());
    }
}
