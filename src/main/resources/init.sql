CREATE DATABASE servletapp WITH OWNER postgres;


CREATE SCHEMA IF NOT EXISTS webapp;


CREATE TABLE IF NOT EXISTS "webapp"."role"
(
    ID       BIGSERIAL PRIMARY KEY,
    ROLENAME TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "webapp"."USER"
(
    USERID          BIGSERIAL   NOT NULL,
    USERNAME        VARCHAR(20) NOT NULL,
    USERLOGIN       VARCHAR(20) NOT NULL,
    USERSURNAME     VARCHAR(20) NOT NULL,
    USERPASSWORD    VARCHAR(30) NOT NULL,
    USEREMAIL       VARCHAR(30) NOT NULL,
    USERDATEOFBIRTH VARCHAR(30) NOT NULL,
    USERSALARY      FLOAT       NOT NULL,
    PRIMARY KEY (USERID)
);

CREATE TABLE IF NOT EXISTS "webapp"."user_roles"
(
    user_id int NOT NULL,
    role_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "webapp"."USER" (USERID) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES "webapp"."role" (ID),
    PRIMARY KEY (user_id, role_id)
);


INSERT INTO "webapp"."USER"
(USERLOGIN, USERPASSWORD, USEREMAIL, USERNAME, USERSURNAME, USERSALARY, USERDATEOFBIRTH)
VALUES ('kat17', 'драсте', 'cat1717@mail.ru', 'Анна', 'Иванова', 200, '10.10.1999');

INSERT INTO "webapp"."USER"
(USERLOGIN, USERPASSWORD, USEREMAIL, USERNAME, USERSURNAME, USERSALARY, USERDATEOFBIRTH)
VALUES ('leadss', 'fdeefe', 'liveliver@gmail.com', 'Максим', 'Вешалкин', 550, '07.09.1990');

INSERT INTO "webapp"."USER"
(USERLOGIN, USERPASSWORD, USEREMAIL, USERNAME, USERSURNAME, USERSALARY, USERDATEOFBIRTH)
VALUES ('great29', 'совсемнегениальныйпароль', 'football_player@gmail.com', 'Вадим', 'Бабурченков', 320,
        '17.01.1880');

INSERT INTO "webapp"."USER"
(USERLOGIN, USERPASSWORD, USEREMAIL, USERNAME, USERSURNAME, USERSALARY, USERDATEOFBIRTH)
VALUES ('honeyMOON', 'пароль', 'smirnovy@gmail.com', 'Василиса', 'Веббер', 400, '15.11.1988');

INSERT INTO "webapp"."USER"
(USERLOGIN, USERPASSWORD, USEREMAIL, USERNAME, USERSURNAME, USERSALARY, USERDATEOFBIRTH)
VALUES ('Sveta', 'gfhjkm', 'svetlanka17@gmail.com', 'Светлана', 'Кветко', 100000, '06.04.2000');



INSERT INTO "webapp"."role" (rolename, id)
VALUES ('user', 1);
INSERT INTO "webapp"."role" (rolename)
VALUES ('admin', 2);
INSERT INTO "webapp"."user_roles" (user_id, role_id)
VALUES (1, 1);
INSERT INTO "webapp"."user_roles" (user_id, role_id)
VALUES (2, 1);
INSERT INTO "webapp"."user_roles" (user_id, role_id)
VALUES (3, 1);
INSERT INTO "webapp"."user_roles" (user_id, role_id)
VALUES (4, 1);
INSERT INTO "webapp"."user_roles" (user_id, role_id)
VALUES (5, 2);