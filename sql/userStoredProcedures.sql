USE pantry;

DROP PROCEDURE IF EXISTS spGetAllUsers;
CREATE PROCEDURE spGetAllUsers()
BEGIN
    SELECT * FROM pantry.Users;
END;

DROP PROCEDURE IF EXISTS spGetUserByFName;
CREATE  PROCEDURE spGetUserByFName(IN first_name varchar(50))
BEGIN
    SELECT * from pantry.Users u WHERE first_name = u.first_name;
END;

DROP PROCEDURE IF EXISTS spGetUserByLName;
CREATE  PROCEDURE spGetUserByLName(IN last_name varchar(50))
BEGIN
    SELECT * from pantry.Users u WHERE last_name = u.last_name;
END;
/*
Used to call sps:
CALL spGetUserByLName('Tomala');
CALL spGetUserByFName('Kenon');
CALL spGetAllUsers();
 */

