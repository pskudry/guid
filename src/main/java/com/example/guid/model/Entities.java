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
public class Entities {

    private  long id;
    private  String name;
    private String description;
}
