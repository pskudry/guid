package com.example.guid.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@ToString
@Data
public class AtributesEntities {

    private long id;
    private long idEntities;
    private String name;
    private long idType;
}
