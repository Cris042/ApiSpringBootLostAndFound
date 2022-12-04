CREATE TABLE IF NOT EXISTS image
(
     id uuid NOT NULL,
     name character varying(255) UNIQUE NOT NULL,
     PRIMARY KEY (id)
);
