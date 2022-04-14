USE pantry;

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

DROP PROCEDURE IF EXISTS spGetUserByUsername;
CREATE PROCEDURE spGetUserByUsername(IN username VARCHAR(50))
BEGIN
    SELECT *
    FROM pantry.Users u
    WHERE username = u.username;
END;

/*
Example sps calls
CALL spGetUserFullName(6);
CALL spGetUserByID(20);
CALL spGetUserByLName('Tomala');
CALL spGetUserByFName('Kenon');
CALL spGetAllUsers();
 */

