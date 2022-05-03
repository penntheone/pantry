USE pantry;

## CREATE TABLE
# -1 = don't like, 0 = no preference, 1 = like
# 0 = Not allergic, 1 = Allergic
# Does not allow duplicates
DROP TABLE IF EXISTS  User_Preferences;
CREATE TABLE User_Preferences (
    pref_id         INT             PRIMARY KEY AUTO_INCREMENT,
    user_id         INT             NOT NULL,
    ingredient_id   INT             NOT NULL,
    stance          INT             NOT NULL    DEFAULT 0,
    isAllergic      BOOLEAN         NOT NULL    DEFAULT 0
);
ALTER TABLE User_Preferences
    ADD UNIQUE INDEX(user_id, ingredient_id);

## CREATE USER'S PANTRY OF INGREDIENTS
-- ----------------------------------------------------------------------------------------------------

## PROC TO ADD PREFERENCES
DROP PROCEDURE IF EXISTS spAddUserPref;
CREATE PROCEDURE spAddUserPref(IN user INT, ingredient INT, pref INT, allergy BOOLEAN)
BEGIN
   SELECT *
         FROM User_Preferences
         WHERE user = user_id AND
               ingredient = ingredient_id;
    INSERT INTO User_Preferences(user_id, ingredient_id, stance, isAllergic)
        VALUES(user, ingredient, pref, allergy);
END;

-- SELECT * FROM Users LIMIT 3;
-- SELECT * FROM Ingredients LIMIT 3;
CALL spAddUserPref(1, 1, -1, 0);
CALL spAddUserPref(1, 2, -1, 1);
CALL spAddUserPref(1, 3, 1, 0);
CALL spAddUserPref(1, 4, 0, 0);
CALL spAddUserPref(1, 344, 0, 1);
-- ----------------------------------------------------------------------------------------------------

## PROC TO GET ALLERGIES
# Used to filer recipe results - recipe ingredient not in call
# Still need to add
DROP PROCEDURE IF EXISTS spGetAllergies;
CREATE PROCEDURE spGetAllergies(IN user INT)
BEGIN
    SELECT RecipeIngredients.recipe_id
    FROM RecipeIngredients
    JOIN User_Preferences ON RecipeIngredients.ingredient_id = User_Preferences.ingredient_id
    WHERE user_id = user AND isAllergic = 1;
END;
## Test call: CALL spGetAllergies(1);

## PROC TO GET LIKES
## PROC TO GET DISLIKES

## PROC TO EDIT PREFERENCES
DROP PROCEDURE IF EXISTS spEditPreferences;
CREATE PROCEDURE spEditPreferences(IN user INT, ingredient INT, stance INT, allergy BOOLEAN)
BEGIN
    UPDATE User_Preferences
    SET User_Preferences.stance = stance, isAllergic = allergy
    WHERE user_id = user and ingredient_id = ingredient;
END;
## Example call - CALL spEditPreferences(1,4,1,0);
-- ----------------------------------------------------------------------------------------------------

## PROC TO DELETE PREFERENCES
DROP PROCEDURE IF EXISTS spRemovePreferences;
CREATE PROCEDURE spRemovePreferences(IN user INT, ingredient INT)
BEGIN
    DELETE
        FROM User_Preferences
        WHERE user_id = user and ingredient_id = ingredient;
END;
-- ----------------------------------------------------------------------------------------------------

# TESTING
-- CALL spAddUserPref(2,6,0,1);
-- SELECT * FROM User_Preferences ORDER BY user_id DESC;
-- CALL spRemovePreferences(2,6);
-- CALL spRemovePreferences(2,8);
-- SELECT * FROM User_Preferences;
SELECT * FROM User_Preferences;
TRUNCATE TABLE User_Preferences;

SELECT * FROM Users LIMIT 1;

