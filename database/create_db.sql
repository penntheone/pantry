
DROP TABLE IF EXISTS Recipes;
CREATE TABLE Recipes (
     id             VARCHAR(50)     PRIMARY KEY
    ,title          VARCHAR(250)    NOT NULL
    ,url            VARCHAR(250)    NOT NULL
    ,n_sodium       DOUBLE          NOT NULL
    ,n_sugar        DOUBLE          NOT NULL
    ,n_protein      DOUBLE          NOT NULL
    ,n_fat          DOUBLE          NOT NULL
    ,n_saturates    DOUBLE          NOT NULL
    ,n_energy       DOUBLE          NOT NULL
    ,instructions   TEXT            NOT NULL
    ,ingredients    VARCHAR(1000)   NOT NULL
    ,ingr_units     VARCHAR(500)    NOT NULL
    ,ingr_quantity  VARCHAR(500)    NOT NULL
);

select * from recipes;

DROP TABLE IF EXISTS Ingredients;
CREATE TABLE Ingredients (
     id             INT             PRIMARY KEY
    ,name           VARCHAR(250)    NOT NULL
    ,n_sodium       DOUBLE          NOT NULL
    ,n_sugar        DOUBLE          NOT NULL
    ,n_protein      DOUBLE          NOT NULL
    ,n_fat          DOUBLE          NOT NULL
    ,n_saturates    DOUBLE          NOT NULL
    ,n_energy       DOUBLE          NOT NULL
);

select * from ingredients where name like('%denny%');

