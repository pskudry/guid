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
public class FullGuid {
    private Long idEntitie;
    private String nameEntitie;
    private Long idAtr;
    private String nameAtr;
    private AttributeType attributeType;
    private Long idValue;
    private String values;
    private Long numRecord;
}
