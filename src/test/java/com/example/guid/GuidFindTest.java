package com.example.guid;

import com.example.guid.dao.AtributeEntitiesRepository;
import com.example.guid.dao.AtributeValuesRepository;
import com.example.guid.dao.EntitiesRepository;
import com.example.guid.dao.FullGuidRepository;
import com.example.guid.init.BaseInit;
import com.example.guid.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JooqTest
public class GuidFindTest extends BaseInit {
    @Autowired
    private EntitiesRepository entitiesRepository;
    @Autowired
    private AtributeEntitiesRepository atributeEntitiesRepository;
    @Autowired
    private AtributeValuesRepository atributeValuesRepository;
    @Autowired
    private FullGuidRepository fullGuidRepository;
// может быть получилось не совсем универсально,но атрибуты в справочниках могут быть разными
    public void findByTooAtr(){
        //Найти всех писателей из России количество книг у которых >15
        final Entities entities = new Entities()
                .setName("Авторы")
                .setDescription("Авторы книг");

        entitiesRepository.addEntitie(entities);
        long idEnt = entitiesRepository.getByName(entities.getName()).getId();

        final List<AtributesEntities> list = Arrays.asList(
                new AtributesEntities().setName("Имя")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.VARCHAR),
                new AtributesEntities().setName("Количество книг")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.INT),
                new AtributesEntities().setName("Страна")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.VARCHAR),
                new AtributesEntities().setName("Жив")
                        .setIdEntities(idEnt)
                        .setAttributeType(AttributeType.BOOLEAN)
        );
        atributeEntitiesRepository.addListAtributes(list);
        final List<Long> atributesEntities = atributeEntitiesRepository.getById(idEnt)
                .stream()
                .map(AtributesEntities::getId)
                .collect(Collectors.toList())
                ;

        final List<AtributesValue> atributesValueList = Arrays.asList(
                new AtributesValue().setValues("Шекспир").setIdAtribut(atributesEntities.get(0)).setNumberRecord(1),
                new AtributesValue().setValues("53").setIdAtribut(atributesEntities.get(1)).setNumberRecord(1),
                new AtributesValue().setValues("Англия").setIdAtribut(atributesEntities.get(2)).setNumberRecord(1),
                new AtributesValue().setValues("FALSE").setIdAtribut(atributesEntities.get(3)).setNumberRecord(1),
                new AtributesValue().setValues("Гофман").setIdAtribut(atributesEntities.get(0)).setNumberRecord(2),
                new AtributesValue().setValues("30").setIdAtribut(atributesEntities.get(1)).setNumberRecord(2),
                new AtributesValue().setValues("Германия").setIdAtribut(atributesEntities.get(2)).setNumberRecord(2),
                new AtributesValue().setValues("FALSE").setIdAtribut(atributesEntities.get(3)).setNumberRecord(2),
                new AtributesValue().setValues("Тургенев").setIdAtribut(atributesEntities.get(0)).setNumberRecord(3),
                new AtributesValue().setValues("20").setIdAtribut(atributesEntities.get(1)).setNumberRecord(3),
                new AtributesValue().setValues("Россия").setIdAtribut(atributesEntities.get(2)).setNumberRecord(3),
                new AtributesValue().setValues("FALSE").setIdAtribut(atributesEntities.get(3)).setNumberRecord(3),
                new AtributesValue().setValues("Дикенз").setIdAtribut(atributesEntities.get(0)).setNumberRecord(4),
                new AtributesValue().setValues("33").setIdAtribut(atributesEntities.get(1)).setNumberRecord(4),
                new AtributesValue().setValues("Англия").setIdAtribut(atributesEntities.get(2)).setNumberRecord(4),
                new AtributesValue().setValues("FALSE").setIdAtribut(atributesEntities.get(3)).setNumberRecord(4),
                new AtributesValue().setValues("Достаевский").setIdAtribut(atributesEntities.get(0)).setNumberRecord(5),
                new AtributesValue().setValues("15").setIdAtribut(atributesEntities.get(1)).setNumberRecord(5),
                new AtributesValue().setValues("Россия").setIdAtribut(atributesEntities.get(2)).setNumberRecord(5),
                new AtributesValue().setValues("FALSE").setIdAtribut(atributesEntities.get(3)).setNumberRecord(5)
        );
        //получаем весь справочник по имени
        List<FullGuid> guide = fullGuidRepository.getfullList(entities.getName());

        // получаем номер записи у которых совпала страна
        Set<Long> recordNumCountry = guide.stream()
         .filter(e -> "Страна".equals(e.getNameAtr()) && "Россия".equals(e.getValues()))
            .map(FullGuid::getNumRecord)
                .collect(Collectors.toSet());

        //новый список уже удолетворяющих условию,что страна Россия
        List<FullGuid> guidebuyRecordNumCountry = new ArrayList<>();
        recordNumCountry.forEach(r-> guidebuyRecordNumCountry.addAll(
                guide.stream()
                        .filter(g-> g.getNumRecord()== r)
                        .collect(Collectors.toList())
                )
        );
        //получаем номера записей у корых соспала страна и количество >15
        Set<Long> recordNumCount = guidebuyRecordNumCountry.stream()
                .filter(e-> "Количество книг".equals(e.getNameAtr()))
                .filter(ee-> new Long(ee.getValues()) > 15)
                .map(FullGuid::getNumRecord)
                .collect(Collectors.toSet());

        // Окончательный список,если нужно можно обрезать до значения Имени
        List<FullGuid> finalList = new ArrayList<>();
        recordNumCount.forEach( rec -> finalList.addAll( guide.stream()
                .filter(g-> g.getNumRecord()== rec)
                .collect(Collectors.toList())));

    }
}
