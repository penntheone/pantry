
-- | Create Users

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                       id              INT             NOT NULL    PRIMARY KEY,
                       username        VARCHAR(50)     NOT NULL,
                       email           VARCHAR(50)     NOT NULL,
                       first_name      VARCHAR(50)     NOT NULL,
                       last_name       VARCHAR(50)     NOT NULL,
                       birthday        VARCHAR(50)     NOT NULL,
                       auth_string     VARCHAR(100)    NOT NULL,
                       is_admin         BIT             NOT NULL,
                       is_staff         BIT             NOT NULL
);

-- | Create Stored Procedures

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

-- | Insert Data

insert into Users VALUES (1, 'jp34', 'test@test.com', 'Joe', 'Kadlic', '2000-04-14', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1, 1);
insert into Users VALUES (2, 'nathan1371', 'test@test.com', 'Nathan', 'Sivak', '1997-06-01', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1, 1);
insert into Users VALUES (3, 'syc', 'test@test.com', 'yc', 's1576k', '1997-06-01', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1, 1);
insert into Users VALUES (4, 'qngson', 'test@test.com', 'Penn', 'Pham', '1997-06-01', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1, 1);
