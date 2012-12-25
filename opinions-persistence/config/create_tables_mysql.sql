DROP DATABASE IF EXISTS OPINIONS;
CREATE DATABASE OPINIONS;
USE OPINIONS;

CREATE TABLE USER(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR (100) NOT NULL,
EMAIL VARCHAR (100) NOT NULL,
REGISTER_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(ID)
);


CREATE TABLE CATEGORY(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR (50),
PRIMARY KEY(ID)
);

CREATE TABLE TAG(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR (50),
PRIMARY KEY(ID)
);


CREATE TABLE SURVEY (
ID INT NOT NULL AUTO_INCREMENT,
CONTENT VARCHAR (500) NOT NULL,
USER_ID INT NOT NULL,
CREATED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
IS_MULTI_OPTIONS VARCHAR(1) NOT NULL,
CATEGORY_ID INT NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(USER_ID) REFERENCES USER(ID),
FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(ID)
);

CREATE TABLE SURVEY_TAG(
SURVEY_ID INT NOT NULL,
TAG_ID INT NOT NULL,
PRIMARY KEY(SURVEY_ID, TAG_ID),
FOREIGN KEY(SURVEY_ID) REFERENCES SURVEY(ID),
FOREIGN KEY(TAG_ID) REFERENCES TAG(ID)
);

CREATE TABLE SURVEY_OPTION_COUNT(
ID INT NOT NULL AUTO_INCREMENT,
SURVEY_OPTION VARCHAR (500),
SURVEY_ID INT,
OPTION_COUNT INT DEFAULT 0,
PRIMARY KEY(ID),
FOREIGN KEY(SURVEY_ID) REFERENCES SURVEY(ID)
);

CREATE TABLE COMMENT(
ID INT NOT NULL AUTO_INCREMENT,
CONTENT VARCHAR(500) NOT NULL,
SURVEY_ID INT NOT NULL,
USER_ID INT NOT NULL,
CREATED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(ID),
FOREIGN KEY(SURVEY_ID) REFERENCES SURVEY(ID),
FOREIGN KEY(USER_ID) REFERENCES USER(ID)
);




