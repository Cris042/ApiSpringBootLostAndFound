
CREATE TABLE IF NOT EXISTS 'thing'
(
    'id' uuid NOT NULL,
    'creation_date' timestamp without time zone NOT NULL,
    'date_update' timestamp without time zone NOT NULL,
    'description' character varying(255) NOT NULL,
    'lost' character varying(255) NOT NULL,
    'name' character varying(255) UNIQUE NOT NULL,
     PRIMARY KEY ('id')
);

CREATE TABLE IF NOT EXISTS 'thingfound' 
(
    'id' uuid NOT NULL,
    'creation_date' timestamp without time zone NOT NULL,
    'id_object uuid' NOT NULL,
    'id_user' bigint NOT NULL,
     PRIMARY KEY ('id'),
     FOREIGN KEY ('id_object') REFERENCES 'thing' ('id')
);

CREATE TABLE IF NOT EXISTS 'image' 
(
    'id' uuid NOT NULL,
    'name' UNIQUE NOT NULL,
     PRIMARY KEY ('id')
);
