-- |----- Create Database ----------------------------------------

DROP DATABASE IF EXISTS pantry;
CREATE DATABASE pantry;
USE pantry;

-- |----- Create Tables ----------------------------------------

CREATE TABLE Users (
                       id              INT             NOT NULL    PRIMARY KEY,
                       username        VARCHAR(50)     NOT NULL,
                       email           VARCHAR(50)     NOT NULL,
                       first_name      VARCHAR(50)     NOT NULL,
                       last_name       VARCHAR(50)     NOT NULL,
                       birthday        VARCHAR(50)     NOT NULL,
                       auth_string     VARCHAR(100)    NOT NULL
);

CREATE TABLE Ingredients (
                             id          INT             NOT NULL    PRIMARY KEY,
                             name        VARCHAR(100)    NOT NULL
);

CREATE TABLE Recipes (
                         id              INT             NOT NULL    PRIMARY KEY,
                         title           VARCHAR(100)    NOT NULL,
                         category        VARCHAR(50)     NOT NULL,
                         region          VARCHAR(50)     NOT NULL,
                         instructions    VARCHAR(5000)   NOT NULL,
                         thumb_url       VARCHAR(100)    NOT NULL,
                         youtube_url     VARCHAR(100)    NOT NULL
);

CREATE TABLE RecipeIngredients (
                                   recipe_id       INT             NOT NULL,
                                   ingredient_id   INT             NOT NULL,
                                   measure         VARCHAR(200)     NOT NULL,
                                   CONSTRAINT FOREIGN KEY (recipe_id)      REFERENCES Recipes(id)      ON DELETE CASCADE   ON UPDATE CASCADE,
                                   CONSTRAINT FOREIGN KEY (ingredient_id)  REFERENCES Ingredients(id)  ON DELETE CASCADE   ON UPDATE CASCADE
);

-- |----- Create Stored Procedures ----------------------------------------

-- |----- User Procedures ----------------------------------------

DROP PROCEDURE IF EXISTS spGetAllUsers;
CREATE PROCEDURE spGetAllUsers()
BEGIN
    SELECT *
    FROM pantry.Users;
END;

DROP PROCEDURE IF EXISTS spGetUserByFName;
CREATE  PROCEDURE spGetUserByFName(IN first_name varchar(50))
BEGIN
    SELECT *
    FROM pantry.Users u
    WHERE first_name = u.first_name;
END;

DROP PROCEDURE IF EXISTS spGetUserByLName;
CREATE  PROCEDURE spGetUserByLName(IN last_name varchar(50))
BEGIN
    SELECT *
    FROM pantry.Users u
    WHERE last_name = u.last_name;
END;

DROP PROCEDURE IF EXISTS spGetUserByID;
CREATE PROCEDURE spGetUserByID(IN id INT)
BEGIN
    SELECT *
    FROM pantry.Users u
    WHERE id = u.id;
END;

DROP PROCEDURE IF EXISTS spGetUserFullName;
CREATE PROCEDURE spGetUserFullName(IN id INT)
BEGIN
    SELECT CONCAT(u.first_name, ' ', u.last_name) AS 'Full Name'
    FROM pantry.Users u
    WHERE id = u.id;
END;

-- |----- Recipe Procedures ----------------------------------------

-- Accounts for case sensitivity and partial matches
DROP PROCEDURE IF EXISTS spGetRecipeByTitle;
CREATE PROCEDURE spGetRecipeByTitle(IN title VARCHAR(100))
BEGIN
    SELECT *
    FROM pantry.Recipes r
    WHERE r.title LIKE CONCAT('%',title,'%');
END;

-- Accounts for case sensitivity
DROP PROCEDURE IF EXISTS spGetRecipeByCategory;
CREATE PROCEDURE spGetRecipeByCategory(IN category VARCHAR(50))
BEGIN
    SELECT *
    FROM pantry.Recipes r
    WHERE r.category = category;
END;

-- Accounts for case sensitivity
DROP PROCEDURE IF EXISTS spGetRecipeByRegion;
CREATE PROCEDURE spGetRecipeByRegion(IN region VARCHAR(50))
BEGIN
    SELECT *
    FROM pantry.Recipes r
    WHERE r.region = region;
END;

DROP PROCEDURE IF EXISTS spGetRecipeByID;
CREATE PROCEDURE spGetRecipeByID(IN id INT)
BEGIN
    SELECT *
    FROM pantry.Recipes r
    WHERE r.id = id;
END;

DROP PROCEDURE IF EXISTS spGetRecipeIDByName;
CREATE PROCEDURE spGetRecipeIDByName(IN name VARCHAR(50))
BEGIN
    SELECT r.id
    FROM pantry.Recipes r
    WHERE r.title = name;
END;

DROP PROCEDURE IF EXISTS spGetAllRecipes;
CREATE PROCEDURE spGetAllRecipes()
BEGIN
    SELECT *
    FROM pantry.Recipes;
END;

-- |----- Ingredient Procedures ----------------------------------------

DROP PROCEDURE IF EXISTS spGetIngredientsByRecID;
CREATE PROCEDURE spGetIngredientsByRecID(IN id INT)
BEGIN
    Select i.id, i.name, ri.measure
    FROM pantry.RecipeIngredients ri
             JOIN pantry.Ingredients i ON ri.ingredient_id = i.id
             JOIN pantry.Recipes r ON ri.recipe_id = r.id
    WHERE r.id = id;
END;
