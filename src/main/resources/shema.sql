DROP SEQUENCE IF EXISTS entities_seq CASCADE;
CREATE SEQUENCE entities_seq;

DROP SEQUENCE IF EXISTS eattributes_type_seq CASCADE;
CREATE SEQUENCE attributes_type_seq;

DROP SEQUENCE IF EXISTS attributes_entities_seq CASCADE;
CREATE SEQUENCE attributes_entities_seq;

DROP SEQUENCE IF EXISTS attribute_values_seq CASCADE;
CREATE SEQUENCE attribute_values_seq;

DROP TABLE IF exists attribute_values;
DROP TABLE IF exists atribute_type;
DROP TABLE IF exists attributes_entities;
DROP TABLE IF exists entities;



CREATE TABLE entities
(
	id BIGINT DEFAULT NEXTVAL('entities_seq'),
    entity_name VARCHAR(100),
    description VARCHAR(4000),
    CONSTRAINT entities_pk PRIMARY KEY (id)
);

CREATE TABLE attributes_type
(
	id BIGINT DEFAULT NEXTVAL('attributes_type_seq'),
    type_name VARCHAR(100),
    CONSTRAINT attributes_type_pk PRIMARY KEY (id)
);
 

CREATE TABLE attributes_entities
(
    id BIGINT DEFAULT NEXTVAL('attributes_entities_seq'),
    attribute_name VARCHAR(100),
    entity_id BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    CONSTRAINT attributes_entities_pk PRIMARY KEY (id),
    CONSTRAINT attr_entities FOREIGN KEY (entity_id) REFERENCES entities (id),
    CONSTRAINT attr_type     FOREIGN KEY (type_id)   REFERENCES attributes_type (id)
);

CREATE TABLE attribute_values
(
	id BIGINT DEFAULT NEXTVAL('attribute_values_seq'),
    attr_id BIGINT NOT NULL,
    record_id BIGINT NOT NULL,
    value VARCHAR(4000),
    CONSTRAINT attribute_values_pk PRIMARY KEY (id),
    CONSTRAINT attr_values_type     FOREIGN KEY (attr_id)   REFERENCES attributes_entities (id)
);