package com.example.guid;

import com.example.guid.dao.AtributeEntitiesRepository;
import com.example.guid.dao.AtributeValuesRepository;
import com.example.guid.dao.EntitiesRepository;
import com.example.guid.init.BaseInit;
import com.example.guid.model.AtributesEntities;
import com.example.guid.model.AtributesValue;
import com.example.guid.model.AttributeType;
import com.example.guid.model.Entities;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@JooqTest
public class CuideServiceTest extends BaseInit {
    @Autowired
    private EntitiesRepository entitiesRepository;
    @Autowired
    private AtributeEntitiesRepository atributeEntitiesRepository;
    @Autowired
    private AtributeValuesRepository atributeValuesRepository;

    @Test
    public void testAddGuide() {
        final Entities entities = new Entities()
                .setName("Department")
                .setDescription("Department of company");

        entitiesRepository.addEntitie(entities);
        long idEnt = entitiesRepository.getByName(entities.getName()).getId();
        final List<AtributesEntities> list = Arrays.asList(
                new AtributesEntities().setName("department name")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.VARCHAR),
                new AtributesEntities().setName("department specialisation")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.VARCHAR)
        );

        atributeEntitiesRepository.addListAtributes(list);
        final List<Long> atributesEntities = atributeEntitiesRepository.getById(idEnt)
                .stream()
                .map(AtributesEntities::getId)
                .collect(Collectors.toList())
                ;

        final List<AtributesValue> atributesValueList = Arrays.asList(
                new AtributesValue().setValues("IT").setIdAtribut(atributesEntities.get(0)).setNumberRecord(1),
                new AtributesValue().setValues("developer company").setIdAtribut(atributesEntities.get(1)).setNumberRecord(1),
                new AtributesValue().setValues("acounting").setIdAtribut(atributesEntities.get(0)).setNumberRecord(2),
                new AtributesValue().setValues("acounting company").setIdAtribut(atributesEntities.get(1)).setNumberRecord(2)
        );

        atributesValueList.forEach(atributesValue -> atributeValuesRepository.addValue(atributesValue));
    }
    @Test
    public void updateAtributeGuide() {
        AtributesEntities atributesEntities = atributeEntitiesRepository.getByName("department name");
        atributesEntities.setName("depatmentNameCompany");
        atributeEntitiesRepository.updateAtribute(atributesEntities);

    }
    @Test
    public void updateValuesAtributeGuide() {
       AtributesValue atributesValue = atributeValuesRepository.selectValue("IT");
       atributesValue.setValues("IT department");
       atributeValuesRepository.updateValue(atributesValue);
    }

    @Test
    public void getAtributevalues() {
        List<AtributesValue> atributesValueList = new ArrayList<>();
        atributesValueList.add(atributeValuesRepository.selectValue("IT department"));
        atributesValueList.add(atributeValuesRepository.selectValue("acounting company"));
    }
    @Test
    public void deleteGuide(){
        Entities entities = entitiesRepository.getByName("Department");
        List<AtributesEntities> atributesEntities = atributeEntitiesRepository.getById(entities.getId());
        List<Long> atributesId = atributesEntities.stream()
                .map(AtributesEntities::getId)
                .collect(Collectors.toList());
        atributesId.forEach(atr -> atributeValuesRepository.deleteByIdAtribute(atr) );
        atributeEntitiesRepository.deleteByIdEntities(entities.getId());
        entitiesRepository.deleteGuidByName(entities.getName());
    }
}
