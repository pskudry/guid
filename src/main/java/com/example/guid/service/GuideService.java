package com.example.guid.service;

import com.example.guid.model.AtributesEntities;
import com.example.guid.model.AtributesValue;
import com.example.guid.model.Entities;

import java.util.List;

public interface GuideService {
    void deleteGuid(final String guideName);
    void editGuid(final String guide);
    void addGuide(final Entities entities, final List<AtributesEntities> atributesEntitiesList);

    void addValausGuid(final List<AtributesValue> atributesValueList);
}
