USE pantry;

DROP PROCEDURE IF EXISTS spGetIngredientsByRecID;
CREATE PROCEDURE spGetIngredientsByRecID(IN id INT)
BEGIN
    Select i.id, i.name, ri.measure
    FROM pantry.RecipeIngredients ri
    JOIN pantry.Ingredients i ON ri.ingredient_id = i.id
    JOIN pantry.Recipes r ON ri.recipe_id = r.id
    WHERE r.id = id;
END;

/*
CALL spGetIngredientsByRecID(3);
*/