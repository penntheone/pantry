USE pantry;

# Table of recipe requests
DROP TABLE IF EXISTS Recipe_Requests;
CREATE TABLE Recipe_Requests (
    request_id              INT             NOT NULL    AUTO_INCREMENT PRIMARY KEY,
    user_id                 INT             NOT NULL,
    submit_date             DATETIME        NOT NULL    DEFAULT (CURRENT_DATE),
    review_date             DATETIME                    DEFAULT NULL,
    isApproved              BOOLEAN         NOT NULL    DEFAULT 0,
    isActive                BOOLEAN         NOT NULL    DEFAULT 1,
    recipe_title            VARCHAR(100)    NOT NULL,
    recipe_category         VARCHAR(50)     NOT NULL,
    recipe_region           VARCHAR(50)     NOT NULL,
    recipe_instructions     VARCHAR(5000)   NOT NULL,
    recipe_thumb_url        VARCHAR(100)    NOT NULL,
    recipe_youtube_url      VARCHAR(100)    NOT NULL,
    comments                VARCHAR(100)    DEFAULT 'Pending Review',
    isAdded                 BOOLEAN         NOT NULL   DEFAULT 0
);
-- ----------------------------------------------------------------------------------------------------
# PROC TO ADD REQUEST
DROP PROCEDURE IF EXISTS spRequestRecipe;
CREATE PROCEDURE spRequestRecipe(IN user INT, title VARCHAR(100), category VARCHAR(50), region VARCHAR(50),
instructions VARCHAR(5000), image VARCHAR(100), video VARCHAR(100))
BEGIN
    INSERT INTO Recipe_Requests(user_id, recipe_title, recipe_category, recipe_region, recipe_instructions,
                               recipe_thumb_url, recipe_youtube_url)
                VALUES(user, title, category, region, instructions, image, video);
END;
## TESTING
/*
CALL spRequestRecipe(1, 'banana soup', 'soup', 'American',
    'mash a banana', 'pic.url', 'testvideo');
CALL spRequestRecipe(1, 'tomato soup', 'soup', 'American',
                     'mash a tomato', 'pic.url', 'testvideo');
CALL spRequestRecipe(1, 'bread soup', 'soup', 'American',
                     'mash a bread', 'pic.url', 'testvideo');
CALL spRequestRecipe(1, 'pineapple soup', 'soup', 'American',
                     'mash a pineapple', 'pic.url', 'testvideo');
CALL spRequestRecipe(1, 'apple soup', 'soup', 'American',
                     'mash an apple', 'pic.url', 'testvideo');
*/
-- ----------------------------------------------------------------------------------------------------
## PROC TO SEE ALL ACTIVE REQUESTS
DROP PROCEDURE IF EXISTS spShowActiveRequests;
CREATE PROCEDURE spShowActiveRequests()
BEGIN
    SELECT *
    FROM Recipe_Requests
    WHERE isActive = 1;
END;

-- CALL spShowActiveRequests();
-- ----------------------------------------------------------------------------------------------------
## PROC TO SEE NUMBER OF PENDING REQUESTS BY USER - MAX 5 pending
DROP PROCEDURE IF EXISTS spNumRequests;
CREATE PROCEDURE spNumRequests(IN user INT)
BEGIN
    SELECT COUNT(*)
    FROM Recipe_Requests
    WHERE user_id = user;
END;

-- CALL spNumRequests(1);
-- ----------------------------------------------------------------------------------------------------
## PROC TO APPROVE A REQUEST
DROP PROCEDURE IF EXISTS spApproveRequest;
CREATE PROCEDURE spApproveRequest(IN id INT)
BEGIN
    UPDATE Recipe_Requests
    SET isApproved = 1, isActive = 0, isAdded = 1, review_date = current_date, comments = 'Approved by admin'
    WHERE request_id = id;
    START TRANSACTION;
    CALL spAddRequestToDatabase(id);
    SELECT * FROM Recipes WHERE Recipes.id > 277;
    SELECT * FROM Recipe_Requests;
    ROLLBACK;
END;

-- CALL spApproveRequest(3); CALL spApproveRequest(4);

-- ----------------------------------------------------------------------------------------------------
## PROC TO SOFT DELETE A REQUEST
DROP PROCEDURE IF EXISTS spDeleteRequest;
CREATE PROCEDURE spDeleteRequest(IN id INT)
BEGIN
    UPDATE Recipe_Requests
    SET isActive = 0, comments = 'Removed by user', review_date = current_date
    WHERE request_id = id;
END;

-- CALL spDeleteRequest(2);
-- ----------------------------------------------------------------------------------------------------
## PROC TO REJECT A REQUEST
DROP PROCEDURE IF EXISTS spRejectRequest;
CREATE PROCEDURE spRejectRequest(IN id INT)
BEGIN
    UPDATE Recipe_Requests
    SET isActive = 0, isApproved = 0, review_date = CURRENT_DATE, comments = 'Rejected by admin'
    WHERE request_id = id;
END;

-- CALL spRejectRequest(1);
-- ----------------------------------------------------------------------------------------------------
## PROC TO ADD RECIPE TO DATABASE
DROP PROCEDURE IF EXISTS spAddRequestToDatabase;
CREATE PROCEDURE spAddRequestToDatabase(IN id INT)
BEGIN
   DECLARE recCount INT DEFAULT 0;
   DECLARE newTitle VARCHAR(100) DEFAULT NULL;
   DECLARE newCat VARCHAR(100) DEFAULT NULL;
   DECLARE newReg VARCHAR(100) DEFAULT NULL;
   DECLARE newInst VARCHAR(100) DEFAULT NULL;
   DECLARE newPic VARCHAR(100) DEFAULT NULL;
   DECLARE newVid VARCHAR(100) DEFAULT NULL;
   SELECT Recipes.id + 1 INTO recCount FROM Recipes ORDER BY Recipes.id DESC LIMIT 1;
   SELECT recipe_title INTO newTitle FROM Recipe_Requests WHERE id = request_id;
   SELECT recipe_category INTO newCat FROM Recipe_Requests WHERE id = request_id;
   SELECT recipe_region INTO newReg FROM Recipe_Requests WHERE id = request_id;
   SELECT recipe_instructions INTO newInst FROM Recipe_Requests WHERE id = request_id;
   SELECT recipe_thumb_url INTO newPic FROM Recipe_Requests WHERE id = request_id;
   SELECT recipe_youtube_url INTO newVid FROM Recipe_Requests WHERE id = request_id;
   INSERT INTO Recipes(id, title, category, region, instructions, thumb_url, youtube_url)
       VALUES(recCount, newTitle, newCat, newReg, newInst,newPic, newVid);
END;
-- ----------------------------------------------------------------------------------------------------
## PROC TO SEE USER's REQUESTS
DROP PROCEDURE IF EXISTS spGetUserRequests;
CREATE PROCEDURE spGetUserRequests(IN user INT)
BEGIN
    SELECT *
    FROM Recipe_Requests
    WHERE user = user_id;
END;
-- ----------------------------------------------------------------------------------------------------

-- ----------------------------------------------------------------------------------------------------
## PROC TO SEE ALL REQUESTS
DROP PROCEDURE IF EXISTS spGetAllRequests;
CREATE PROCEDURE spGetAllRequests()
BEGIN
    SELECT *
    FROM Recipe_Requests;
END;
-- ----------------------------------------------------------------------------------------------------
## Testing
SELECT * FROM Recipe_Requests;

call spGetAllRequests();

-- TRUNCATE Recipe_Requests;


