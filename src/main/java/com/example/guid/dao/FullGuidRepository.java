package com.example.guid.dao;

import com.example.guid.dao.rel.AtributeEntitiesRel;
import com.example.guid.dao.rel.AtributeValuesRel;
import com.example.guid.dao.rel.EntitiesRel;
import com.example.guid.model.FullGuid;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class FullGuidRepository {

    private final DSLContext dslContext;

    public List<FullGuid> getfullList(String name ){
       return dslContext.select(EntitiesRel.ID.as("idEntitie"),
               EntitiesRel.NAME.as("nameEntitie"),
               AtributeEntitiesRel.ID.as("idAtr"),
               AtributeEntitiesRel.NAME.as("nameAtr"),
               AtributeEntitiesRel.ATTR_TYPE.as("attributeType"),
               AtributeValuesRel.ID.as("idValue"),
               AtributeValuesRel.VALUE.as("values"),
               AtributeValuesRel.RECORD_ID.as("numRecord")
               )
               .from(EntitiesRel.INSTANCE)
               .join(AtributeEntitiesRel.INSTANCE).on(AtributeEntitiesRel.ID_ENTITIES.eq(EntitiesRel.ID))
               .join(AtributeValuesRel.INSTANCE).on(AtributeEntitiesRel.ID_ENTITIES.eq(AtributeValuesRel.ID_ATRIBUTE))
               .fetch().into(FullGuid.class);
    }
}
