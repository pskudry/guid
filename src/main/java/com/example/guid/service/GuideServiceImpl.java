package com.example.guid.service;

import com.example.guid.dao.AtributeEntitiesRepository;
import com.example.guid.dao.AtributeValuesRepository;
import com.example.guid.dao.EntitiesRepository;
import com.example.guid.model.AtributesEntities;
import com.example.guid.model.AtributesValue;
import com.example.guid.model.Entities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService{
    private EntitiesRepository entitiesRepository;
    private AtributeEntitiesRepository atributeEntitiesRepository;
    private AtributeValuesRepository atributeValuesRepository;

    @Override
    public void addGuide(Entities entities, List<AtributesEntities> atributesEntitiesList) {
        if( entitiesRepository.getByName(entities.getName())== null) {
            entitiesRepository.addEntitie(entities);
            long idEntities = entitiesRepository.getByName(entities.getName()).getId();
            atributesEntitiesList.forEach(atributesEntities -> atributesEntities.setIdEntities(idEntities));
            atributeEntitiesRepository.addListAtributes(atributesEntitiesList);
        } else {
            entitiesRepository.updateEntitie(entities);
        }

    }

    @Override
    public void deleteGuid(String guideName) {
      Entities entities = entitiesRepository.getByName(guideName);
      List<AtributesEntities> atributesEntities = atributeEntitiesRepository.getById(entities.getId());
      List<Long> atributesId = atributesEntities.stream()
              .map(AtributesEntities::getId)
              .collect(Collectors.toList());
      atributesId.forEach(atr -> atributeValuesRepository.deleteByIdAtribute(atr) );
      atributeEntitiesRepository.deleteByIdEntities(entities.getId());
      entitiesRepository.deleteGuidByName(entities.getName());
    }

    @Override
    public void editGuid(String guide) {
        Entities entities = entitiesRepository.getByName(guide);
        entitiesRepository.updateEntitie(entities);
    }

    @Override
    public void addValausGuid(List<AtributesValue> atributesValueList) {
        atributesValueList.forEach(atributesValue -> atributeValuesRepository.addValue(atributesValue));
    }
}
