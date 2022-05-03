USE pantry;

DROP PROCEDURE IF EXISTS spBasicAdvancedSearch;
CREATE PROCEDURE spBasicAdvancedSearch(IN name VARCHAR(100), region VARCHAR(20), category VARCHAR(20))
    BEGIN
        SELECT *
        FROM Recipes r
        WHERE
            r.title     LIKE CONCAT('%', name, '%') AND
            r.region    LIKE CONCAT('%', region, '%') AND
            r.category  LIKE CONCAT('%', category, '%');
    End;

-- Will search by fields, checkbox for likes/dislikes, and allergies

SELECT * FROM User_Preferences WHERE user_id = 1;

/*
CALL spBasicAdvancedSearch('', 'British', 'Dessert');
Call spBasicAdvancedSearch('', '', 'Dessert');

SELECT category, count(*)
from Recipes
group by category

CALL spGetRecipeByTitle("Chicken");
CALL spGetIngredientsByRecID(25);
 */
