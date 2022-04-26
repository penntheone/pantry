
-- | Create Users

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
                       id              INT             NOT NULL    PRIMARY KEY,
                       username        VARCHAR(50)     NOT NULL,
                       email           VARCHAR(50)     NOT NULL,
                       first_name      VARCHAR(50)     NOT NULL,
                       last_name       VARCHAR(50)     NOT NULL,
                       birthday        VARCHAR(50)     NOT NULL,
                       auth_string     VARCHAR(100)    NOT NULL
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

insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (1, 'atomala0', 'atomala0@whitehouse.gov', 'Adria', 'Tomala', '1992-10-14', '34037fba57f075a1b178253bf71f69da889c3ec0962e3c300c67cbe2d7e4f2de');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (2, 'klongforth1', 'klongforth1@tiny.cc', 'Kenon', 'Longforth', '1997-06-01', '1c822af4218bf01081fda5a162416950172840a33d91d178546bce331ac45054');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (3, 'nnoone2', 'nnoone2@ed.gov', 'Nancey', 'Noone', '2003-05-04', 'aac60e40a337bf42252176add966c08e8c28dd5981e79b82a844bd3fa550459d');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (4, 'amacdermot3', 'amacdermot3@elegantthemes.com', 'Angeline', 'MacDermot', '2001-01-29', '9d8ef395e87148a37114c1e5b9c165c343ddc5c447e5b585c61289ef35600f22');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (5, 'mjaggard4', 'mjaggard4@cpanel.net', 'Malynda', 'Jaggard', '1980-08-09', '8a0b71af0e399588d9e1e82b16816917336bac42a4a39a1972e1d839c56b67fc');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (6, 'jcutriss5', 'jcutriss5@ucoz.com', 'Jakob', 'Cutriss', '2000-04-23', '95063c1df189898bc034bdec894d1fb572eb838745521f861ade590143572e09');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (7, 'lchewter6', 'lchewter6@archive.org', 'Lockwood', 'Chewter', '2011-04-05', '6dd2f19210a390db16da6e3a4af4787d79edf502fa66e9d70d2b28c41f1c16b0');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (8, 'glivesey7', 'glivesey7@huffingtonpost.com', 'Gonzales', 'Livesey', '2013-07-23', 'a2f4c6522392e7ac2ad67e998c86390c9fc53b36aa470a525cfb9eface410469');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (9, 'ldevigne8', 'ldevigne8@mlb.com', 'Lotti', 'Devigne', '2012-06-10', '1faf6e930b161209c4f022bf99d71949b563157da101a38f8317f2e374750ecd');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (10, 'sscoon9', 'sscoon9@a8.net', 'Stearne', 'Scoon', '1989-08-30', 'a3cad02b069ea3d15f958d4e43247dc648cb9a0b777c76b0d6baccd0d0125bf9');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (11, 'edanseya', 'edanseya@cam.ac.uk', 'Eloisa', 'Dansey', '1990-11-02', 'dfbf4c40d93b33a0e5a41c3e76a41b9f98c7a5a464508d8f1ec625345c5fb92f');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (12, 'oclendeningb', 'oclendeningb@howstuffworks.com', 'Othello', 'Clendening', '2014-09-11', '2dad04f8efa2d57b1a2a7d507c7d58870c68913e2619edaa8fe06bdc118ec483');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (13, 'bbuardc', 'bbuardc@chronoengine.com', 'Bert', 'Buard', '1989-04-08', '8f71ca69403c11300ab081f13140cbf37110f050d073d2b2155ad52efe3c9260');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (14, 'isurmand', 'isurmand@japanpost.jp', 'Ina', 'Surman', '2013-11-10', '2f9a1b040fa871e99efbb03c8dbf1ed2d63d2b7ab4faf46a47e684ae8f895f33');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (15, 'dderisleye', 'dderisleye@booking.com', 'Daffie', 'Derisley', '1990-08-02', '9720e3c555f642c4d834af022722ea3888754d5d5af4bbf41c924d82aa178466');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (16, 'dwimletf', 'dwimletf@wikimedia.org', 'Daveen', 'Wimlet', '1999-10-08', '18e966eed9041364ba6c3ce7c6a416376b8a16e019fd01bc6f00032091c2c7d4');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (17, 'hgrisardg', 'hgrisardg@bizjournals.com', 'Holden', 'Grisard', '2006-03-31', '68d5bbf982b75cce1a0b5e67f3674665d428e7755e544d944fa0abab3a4d2931');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (18, 'kchealesh', 'kchealesh@posterous.com', 'Killie', 'Cheales', '1994-03-30', 'fc3496911d912e70fa1e0d280c651419585a2f6886c4d0d120ac08a2bc3a625c');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (19, 'fskethi', 'fskethi@wunderground.com', 'Frayda', 'Sketh', '1994-06-07', '39cef7ebef36e7118d4794f051ae1f26661f070658b53d56a898cde99d0024b5');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (20, 'pwoodrowj', 'pwoodrowj@reddit.com', 'Palmer', 'Woodrow', '1995-09-08', 'e274a97e22cdef90739065a7268e4f036bebfc007fc1e6c692eb8ec9b27d533d');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (21, 'econradsenk', 'econradsenk@angelfire.com', 'Etti', 'Conradsen', '1987-02-13', 'cacbf81929cdc46879b8d2b09bf29134ef85949dbf2ea8b0f3a5d240f2004623');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (22, 'gjohannesl', 'gjohannesl@intel.com', 'Giustino', 'Johannes', '1997-01-26', 'fe47fc8f3749c213b4c1e1d71a45ff652a6b3cb488da53c7f2449cec94fdafe7');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (23, 'jdikelsm', 'jdikelsm@cdbaby.com', 'Jimmie', 'Dikels', '1983-01-16', '631033c3e97deba8d24f93462fdf49b0f4c49253245cfaaec6157288336043e0');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (24, 'khuzzayn', 'khuzzayn@discuz.net', 'Kathlin', 'Huzzay', '1994-07-06', '1cdd9bdccee66df30f6ff354fc8d5ce26a6857cb6914607804effd55aa90c723');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (25, 'adefrieso', 'adefrieso@uiuc.edu', 'Aryn', 'Defries', '2010-04-23', '67ae3a73112f08fcb3fe639f53dc671d7675ec8d6c87c6d3fc81a5dc19d64312');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (26, 'gwhelanp', 'gwhelanp@free.fr', 'Gillie', 'Whelan', '2014-11-16', '170afa58218e7f11f26dce52b6a6dc826259d93b0b2832750f517da095fb3d42');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (27, 'estartq', 'estartq@amazon.co.jp', 'Erastus', 'Start', '2014-09-08', '88c71c5f7f1ffae7a926e179af650d7d4f0705d92d68a93dca5e9e30a5f5d88e');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (28, 'iwooddisser', 'iwooddisser@mtv.com', 'Ibbie', 'Wooddisse', '2008-05-29', 'a981de94cc82bc818454b795c7a2de1edb9e3181b36a2c5a0ae9a7b1369c2686');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (29, 'ltroaks', 'ltroaks@pen.io', 'Lottie', 'Troak', '1994-08-12', 'f73098557508780765f6f431d95291b973661cd23d7d05f7fa5b6d4d16e91303');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (30, 'rcoggont', 'rcoggont@illinois.edu', 'Rogerio', 'Coggon', '2009-06-13', '3c452e79f07dbfb4e57c5f92cc1516296d080dbfdabbf4a62cbb275cc4afc542');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (31, 'lmichalu', 'lmichalu@zdnet.com', 'Lammond', 'Michal', '2013-07-17', 'bfdd85336e71670817c31e643f22aa411e32263d8a68d87ebaca411ad62169d2');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (32, 'jappleyardv', 'jappleyardv@icio.us', 'Juanita', 'Appleyard', '1985-01-10', 'caf87323e29c13504066211010dd66678b7d8a9035d1ede41299774f221df76');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (33, 'bransfieldw', 'bransfieldw@dot.gov', 'Bliss', 'Ransfield', '1995-08-31', 'fb8fed41678d56a2b7fe352cee4cf9a166fdffb24e3dcfb226ed67412b2b1cac');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (34, 'gsheersx', 'gsheersx@sohu.com', 'Giuseppe', 'Sheers', '1995-06-10', 'ef6b4ac6f6274755a510567f7376fac0441829ba209bad4ffe0a45d3cc59cb14');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (35, 'mbuffy', 'mbuffy@youku.com', 'Marcille', 'Buff', '1998-06-22', '629ceff13f6307a87960eb082e5adc13f4b103bcb1038c3249ecc1300745629b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (36, 'qmordeyz', 'qmordeyz@businesswire.com', 'Quintin', 'Mordey', '2014-07-01', '341fffaade7336c3d83ee764a7a446c00f879593a5051f754d450928b944c55b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (37, 'mlotte10', 'mlotte10@cdc.gov', 'Marcos', 'Lotte', '1985-11-23', 'f731e87b50b4dec5e8aefcb830307291b96739ff6b5b4df85db1445c51127c5f');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (38, 'ainott11', 'ainott11@reddit.com', 'Alfredo', 'Inott', '2003-10-15', '3dbcf41b23e24303e4f71ecaebdc79a4cf5bf3c8331b687fc77e494bc552752b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (39, 'skopman12', 'skopman12@vkontakte.ru', 'Sue', 'Kopman', '1998-12-30', '1a29a87a98bb151dfdc43a9ccb05b33b02227f12c067e695d8a6f467875828fd');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (40, 'ebuckby13', 'ebuckby13@smh.com.au', 'Em', 'Buckby', '2001-12-02', '47d04d5b20fc9267912c71e3e1d2f30d57765e79a48c6172fe412f21a3603567');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (41, 'ogayden14', 'ogayden14@guardian.co.uk', 'Obie', 'Gayden', '2012-09-21', 'af2e4e7fd91db635195953889a7858d3c5611af215325936468eac7f4d5de874');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (42, 'wtrain15', 'wtrain15@xinhuanet.com', 'Wyndham', 'Train', '2006-02-28', 'a181818b23ecd7c5c17ab27a2cfa0d0b24f5be0d85275b66c046eb2604bf540a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (43, 'ctrevers16', 'ctrevers16@digg.com', 'Conant', 'Trevers', '1991-10-24', '287bbf8568c16323faed5f99f19af5a27a17fdbb024695a9704500d4a1a146cc');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (44, 'mbonnor17', 'mbonnor17@va.gov', 'Moss', 'Bonnor', '2006-03-17', '965fbd70d47bb90b550368da93706c1ccbff0c810f299a11bba33e656178e60d');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (45, 'lilyushkin18', 'lilyushkin18@squidoo.com', 'Lind', 'Ilyushkin', '2014-03-07', '48a9682acfa51e357fef9d37e6eda0c9abe56044a28e6e9200bff9a1b0e96986');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (46, 'cbriton19', 'cbriton19@yahoo.com', 'Cyril', 'Briton', '2009-02-01', 'a83a5f8185b27aa3a689db44e10ba54eb694387e9c3bf24c0955c3bdbacb8458');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (47, 'vsteeples1a', 'vsteeples1a@bizjournals.com', 'Valerie', 'Steeples', '2003-05-20', '1522effdfdae9ccd46f7c876714e4bae78566efb76d103185f442013fea86c06');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (48, 'astandishbrooks1b', 'astandishbrooks1b@rambler.ru', 'Alleyn', 'Standish-Brooks', '1982-01-16', '4ef4c0b33695f3fc91bbe6099047dd5f2c12d37c0efb2c9c79b3bcb35d65836e');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (49, 'aschmidt1c', 'aschmidt1c@npr.org', 'Adriana', 'Schmidt', '1981-08-06', '6ecc3b1c287847a911c9a220d9b5df80cc855a3e41bda33c55fa27998cfea065');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (50, 'hgoodwill1d', 'hgoodwill1d@usatoday.com', 'Helenka', 'Goodwill', '2009-02-22', '9adc1fd6436017d10508fb75e2a8f81794d7d4c1139c8ce35a47eaa7612274a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (51, 'bcreasey1e', 'bcreasey1e@rambler.ru', 'Brier', 'Creasey', '2014-05-29', '7afc109aade9fa26be3f6f428b0494ceb15fb7a01931e5577af08b5e6ce261cb');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (52, 'jgowdridge1f', 'jgowdridge1f@furl.net', 'Jasmine', 'Gowdridge', '1997-12-17', '29bc5ff56c4ba5dec2f3168cc8b043d3322fbb56ec67c7a69bdd69e095d0cfd6');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (53, 'frisbrough1g', 'frisbrough1g@tmall.com', 'Felipe', 'Risbrough', '1982-04-02', '7ebe0cbf26fa292beee381534ca2d97a2f4a7ffd3eece53d39133253d3e0f17e');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (54, 'slurriman1h', 'slurriman1h@tamu.edu', 'Sherilyn', 'Lurriman', '1985-06-13', '522760556a4dbd96a03ff5d62ac68194a4e6350607abfcc6cf5b10d2871c4099');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (55, 'mowers1i', 'mowers1i@go.com', 'Muffin', 'Owers', '1994-09-20', '489a29908840d5de049dd645d621890c53419efc1209643ae621cd3d28a1f72a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (56, 'ltarney1j', 'ltarney1j@pen.io', 'Lilly', 'Tarney', '1985-01-04', '9eab96132177b59df030e50e9ead8b8b10550a0d55c06254028c7bdbf4abef2d');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (57, 'bpregel1k', 'bpregel1k@ebay.com', 'Belle', 'Pregel', '2012-09-04', '9b0f424ae179b40b63fc69df8964733b6f07fa222f0dc9486a121b55d964ce3a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (58, 'lrockingham1l', 'lrockingham1l@friendfeed.com', 'Leroi', 'Rockingham', '1990-10-07', '1b4c6a52679153ce424816b404d99986feeae7f11b5a90d740b6a7b70219c5e7');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (59, 'rboggish1m', 'rboggish1m@examiner.com', 'Roderick', 'Boggish', '1999-11-28', 'd70f2dc21fc28a0d417002db8e6dfc842d7e61722b8ecddfcaee5b055f4d82aa');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (60, 'rmcgeown1n', 'rmcgeown1n@flavors.me', 'Rodie', 'McGeown', '1980-08-03', 'a5c494a824e083bf86b3e70d7542e84f6efd8c666bde9e4b7c62a4f3ac00656c');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (61, 'brubica1o', 'brubica1o@nih.gov', 'Basil', 'Rubica', '1990-10-07', '57b237df920bf783f908b816568a8b1966bbac562b602e3235f34e1f263ed7c3');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (62, 'lseargeant1p', 'lseargeant1p@china.com.cn', 'Laetitia', 'Seargeant', '1988-08-09', '7c9c5197284353ea4c58040a2c083336092b94e17a9e5aeff8c43c5b6d818efd');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (63, 'adomokos1q', 'adomokos1q@wunderground.com', 'Annis', 'Domokos', '2005-09-27', '9474288a53fa0e753578b79fd866a1a13c8aa7fa1992bd1ffe7102136d38f6f0');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (64, 'pdominichetti1r', 'pdominichetti1r@people.com.cn', 'Pearline', 'Dominichetti', '1991-01-28', 'db99c80a71a807a748d67fee3a2c9cb37c77c2ddec77263781ee404b45c3956a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (65, 'gfielder1s', 'gfielder1s@surveymonkey.com', 'Grazia', 'Fielder', '1986-09-05', '726a657143d49d2173461b799547b9b87474f34beaf6cf661f751de79b2d6467');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (66, 'eshailer1t', 'eshailer1t@dmoz.org', 'Edie', 'Shailer', '2002-12-20', '9a9d714548e4d19c7b744f079bec20ec7349de107ac2c9e1d2bd11c9d93e880d');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (67, 'fklagges1u', 'fklagges1u@skype.com', 'Fan', 'Klagges', '1990-07-02', '6670fc7f7c11bbb76d5cceb8ddf1c281fa791c352ecfc043388a3cc81ff1292a');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (68, 'pbrown1v', 'pbrown1v@zimbio.com', 'Paulita', 'Brown', '1983-12-17', '286e1fcc2aee0f8669235ffdb260abf2f9e6e77ba2db236e55c4ce9c6eea2552');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (69, 'lbrocks1w', 'lbrocks1w@pinterest.com', 'Lesli', 'Brocks', '1998-11-22', 'a66707c2175d4ab54afdd7980ff5137652adb0a7fe9ecca4e2bdededeae1fd4e');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (70, 'mscathard1x', 'mscathard1x@csmonitor.com', 'Morgan', 'Scathard', '2003-12-31', 'e8b5d694755df65036e25db7f8c8e09b92166aa72e769eb84ba03242fc845343');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (71, 'fyardy1y', 'fyardy1y@cloudflare.com', 'Ferdinande', 'Yardy', '2000-07-08', '2ea5cdd49caea0ca4cf8ba40b72bf7f1a1dd559f254d6262aa0574b4cfa42593');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (72, 'gochterlony1z', 'gochterlony1z@eepurl.com', 'Gigi', 'Ochterlony', '1995-04-18', 'acf5011998b175d7a0a3272719afd94d2d8493eda904ae4b0b7848fc82946f60');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (73, 'hyannoni20', 'hyannoni20@hp.com', 'Hadrian', 'Yannoni', '2000-12-24', 'e00d919573618e5abd2f20fbae40c272a1bb635bcd3e7673ec9d1e440ea44aca');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (74, 'mruler21', 'mruler21@delicious.com', 'Marni', 'Ruler', '2009-08-21', 'd5add99164d899e46b617098da02b9651c664a71175b7e4065b358f3c81c6758');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (75, 'mnarey22', 'mnarey22@bbb.org', 'Marje', 'Narey', '1991-09-09', '704fee133f3e900f91eb5b16cf321f32c5021e83511beecbcb08a8ca0d7bd37');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (76, 'hmazey23', 'hmazey23@de.vu', 'Harv', 'Mazey', '2013-03-18', '362f4308b1b727baa2062236c8ecb84a5ba50d8854dd70110579dc84a1703de');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (77, 'ldomoni24', 'ldomoni24@delicious.com', 'Lanni', 'Domoni', '1980-12-24', 'd9f69a1bcbc8dc2a1592e193db784fee189828a2d632af7e620f93f4b42d1337');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (78, 'lbiernat25', 'lbiernat25@elpais.com', 'Lorianna', 'Biernat', '1985-11-02', 'c577393c70897d315361c8a09f58350c15e28bddb611bdb28500f3045aa437d8');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (79, 'jgraber26', 'jgraber26@mashable.com', 'Jeannine', 'Graber', '2003-10-16', '5874450a5c50ab8278a50f6189139540f49c754d5ee55d9bdddd9b7b509976cb');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (80, 'ahealey27', 'ahealey27@tripod.com', 'Annmarie', 'Healey', '1986-08-24', '50a52207016b5555d3a86ec2fa8c7fbb8cb0afa61be8b4a3395ac8f3b4c94761');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (81, 'hmatuschek28', 'hmatuschek28@mediafire.com', 'Heddi', 'Matuschek', '1988-09-15', 'd3582e5952e1434e7191f9f7e74071db333ebcdbf2c23e1d80e9d701957f8b2');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (82, 'apoller29', 'apoller29@who.int', 'April', 'Poller', '2008-07-28', 'bdb0b0150c3ba01ac3f62599e91d0075ebfaa3cc71e3fdce41f608ffa0aae9a6');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (83, 'rcandy2a', 'rcandy2a@fotki.com', 'Raimondo', 'Candy', '1987-04-23', 'd3184dafe3c72dbbf96fe6c7d1fb6af66d893a8fc9833be35dbbd88bd68ab5b1');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (84, 'llupton2b', 'llupton2b@fc2.com', 'Langston', 'Lupton', '1995-11-14', '10ccadf7e16d475c8e4425a95552d59dce348c5ce226255800ec992bfb3b037c');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (85, 'hpandya2c', 'hpandya2c@imgur.com', 'Harper', 'Pandya', '2004-01-26', 'c30242681ed2022f131932714ba55ff46e31b81658135ab1ae529e796760d30b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (86, 'tkuhnhardt2d', 'tkuhnhardt2d@simplemachines.org', 'Tan', 'Kuhnhardt', '1981-01-23', 'bb6a7c7a405334fddbac0d8a575fe73d230fb3ce3004d72911762d72f56f987b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (87, 'mprevett2e', 'mprevett2e@china.com.cn', 'Mariette', 'Prevett', '2004-06-06', 'affe09a90f0379c09ef2ba8e4e0eb7867dbc08d3cfbf718952a3a9913b8fc464');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (88, 'vlayton2f', 'vlayton2f@51.la', 'Verge', 'Layton', '1984-02-26', 'b667fed7e455433d963c35b5d601fdc954f520e22bdd287e85a1d8d961fb9420');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (89, 'rinnocenti2g', 'rinnocenti2g@t.co', 'Rubin', 'Innocenti', '1984-02-12', 'a0cb346352a342df92acd1dd512cb9f64008dc10b6a21233815712d3e927662f');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (90, 'ahanlon2h', 'ahanlon2h@hao123.com', 'Alisa', 'Hanlon', '1989-12-05', '67451d11c4d54d467e5b8a9ceaa7c333a4f95d7f2347c380d4c9239e8183ee76');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (91, 'cocarroll2i', 'cocarroll2i@indiatimes.com', 'Christyna', 'O\'Carroll', '2007-07-29', '5a3a0b3e51fccaa0f85b42d8fd95f73c7085cd0a408f72f60692742f0d031797');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (92, 'tbottrill2j', 'tbottrill2j@arstechnica.com', 'Tally', 'Bottrill', '1986-02-26', 'f1f295edc790f9044d817849a8ca4547a42e54d759e45fd45c2080410ebfd05f');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (93, 'tlambol2k', 'tlambol2k@princeton.edu', 'Tildie', 'Lambol', '1984-03-23', '28f07d6a1c3cd52a0232d2b7867dc3e478a754dc4ca6613e253db7fa68b64d18');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (94, 'ehousley2l', 'ehousley2l@patch.com', 'Ellery', 'Housley', '2011-11-14', '897885e7f921946ebaafc9ca241e2539135849d423c2dd354505c6851201fff8');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (95, 'btruelock2m', 'btruelock2m@ustream.tv', 'Britt', 'Truelock', '2008-01-13', 'c554eb6919ad6e993ea9bd351c5c08462d47ab1b5837198405301635412884ba');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (96, 'jmushart2n', 'jmushart2n@nature.com', 'Jo', 'Mushart', '1981-09-27', '90b9caa85f569c0903deacc5ab7e3bc3ee735aafffd2332c1d0c82536037f619');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (97, 'jamphlett2o', 'jamphlett2o@taobao.com', 'Jehu', 'Amphlett', '1989-07-17', '79d17e2f88c372651f0ba36688669f02d124ebfe38df777a6ac1251597db884b');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (98, 'tferon2p', 'tferon2p@chron.com', 'Timothee', 'Feron', '1981-12-01', '62cdf5db0bead30c27272e2b9acf593933eb886391d1b6114c10ae90abfdc4e3');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (99, 'mbes2q', 'mbes2q@statcounter.com', 'Maureene', 'Bes', '1983-04-07', '25e986580b117fe7301b92529c7af09d7c3ba8b2ee762247d6eac76ad7a76275');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (100, 'awyness2r', 'awyness2r@theglobeandmail.com', 'Afton', 'Wyness', '1985-10-08', '4f312a470972c0f4a90c62ab3996b086037c0719369f7a123688ee93e0819a37');
insert into Users (id, username, email, first_name, last_name, birthday, auth_string) values (101, 'test', 'test@test.com', 'Test', 'User', '2000-01-01', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
