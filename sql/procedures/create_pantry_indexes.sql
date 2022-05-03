Use pantry;

CAll spGetRecipeByTitle('Chicken');
-- To create index:
ALTER TABLE Recipes
ADD index idx_rp_title (title);

-- To Remove index:
-- DROP INDEX idx_rp_title ON Recipes

ALTER TABLE Ingredients
    ADD INDEX idx_ingredient_name (name);
-- DROP INDEX idx_ingredient_name ON Ingredients;

ALTER TABLE RecipeImages
    ADD INDEX idx_images_id (recipe_id);
-- DROP INDEX idx_images_id ON RecipeImages


SELECT COUNT(*), recipe_id
FROM Recipes
join RecipeIngredients RI on Recipes.id = RI.recipe_id
group by recipe_id
order by count(*) desc
