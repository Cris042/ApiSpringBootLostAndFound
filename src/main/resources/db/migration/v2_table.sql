CREATE TABLE IF NOT EXISTS 'image' 
(
    'id' uuid NOT NULL,
    'name' UNIQUE NOT NULL,
     PRIMARY KEY ('id')
);
