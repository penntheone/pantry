
-- | Create Recipe Images

DROP TABLE IF EXISTS RecipeImages;
CREATE TABLE RecipeImages (
                              recipe_id       INT             NOT NULL    PRIMARY KEY,
                              image           MEDIUMBLOB      NOT NULL
);
