USE pantry;

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


/*
Example sps calls
CALL spGetAllRecipes();
CALL spGetAllRecipes();
CALL spGetRecipeIDByName('Braised Beef Chilli');
CALL spGetRecipeByRegion('Mexican');
CALL spGetRecipeByID(43);
CALL spGetRecipeByCategory('chicken');
CALL spGetRecipeByTitle('beef Wellington');
CALL spGetRecipeByTitle('beef');
 */