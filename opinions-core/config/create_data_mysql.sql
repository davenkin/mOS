INSERT INTO USER (NAME, EMAIL, PASSWORD) VALUES ('davenkin', 'davenkin@gmail.com', MD5('davenkin'));
INSERT INTO USER (NAME, EMAIL, PASSWORD) VALUES ('tom', 'tom@gmail.com', MD5('tom'));
INSERT INTO USER (NAME, EMAIL, PASSWORD) VALUES ('kate', 'kate@gmail.com', MD5('kate'));
INSERT INTO USER (NAME, EMAIL, PASSWORD) VALUES ('eudy', 'eudy@gmail.com',MD5('eudy'));

INSERT INTO CATEGORY VALUES ('001', 'Science');
INSERT INTO CATEGORY VALUES ('002', 'Economy');
INSERT INTO CATEGORY VALUES ('003', 'Culture');
INSERT INTO CATEGORY VALUES ('004', 'Politics');
INSERT INTO CATEGORY VALUES ('005', 'Education');

INSERT INTO TAG (NAME) VALUES ('computer');
INSERT INTO TAG (NAME) VALUES ('programming');
INSERT INTO TAG (NAME) VALUES ('music');
INSERT INTO TAG (NAME) VALUES ('law');
INSERT INTO TAG (NAME) VALUES ('market');
INSERT INTO TAG (NAME) VALUES ('book');
INSERT INTO TAG (NAME) VALUES ('automobile');
INSERT INTO TAG (NAME) VALUES ('game');
INSERT INTO TAG (NAME) VALUES ('internet');
INSERT INTO TAG (NAME) VALUES ('university');
INSERT INTO TAG (NAME) VALUES ('airplane');
INSERT INTO TAG (NAME) VALUES ('job');
INSERT INTO TAG (NAME) VALUES ('city');
INSERT INTO TAG (NAME) VALUES ('business');
INSERT INTO TAG (NAME) VALUES ('tourism');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'How many years have you been programming?',1,'N','001');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'How many books did you read last year?',2,'N','003');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'What kind of books do you like?',3,'Y','003');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'Who do you like best?',3,'N','004');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'How many computers have you ever being using?',1,'N','003');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'How many companies have you ever being working for?',1,'N','002');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'Do you think China will surpass USA in the next 30 years?',1,'N','005');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'Is science good or evil?',2,'N','001');

INSERT INTO SURVEY(CONTENT,USER_ID, MUL_OPT, CATEGORY_CODE) VALUES(
'Which city have you ever being to?',2,'Y','003');



INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (1,'computer');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (9,'business');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (3,'book');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (2,'book');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (5,'computer');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (9,'tourism');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (6,'job');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (1,'programming');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (5,'internet');
INSERT INTO SURVEY_TAG (SURVEY_ID, TAG_NAME) VALUES (9,'city');


INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (1,'More than 10 years');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (2,'Less than 10');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (1,'5-10 years');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (2,'More than 10');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (3,'Science fictions');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (5,'More than 3');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Shang Hai');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (3,'Love stories');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'3');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'2');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (3,'History');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (5,'1');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (8,'No');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (5,'2');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'1');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'4');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (7,'No');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Shen Zhen');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (5,'3');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'5');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (1,'Less than 5 years');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (6,'More than 5');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Cheng Du');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (7,'Yes');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (7,'Not sure');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (8,'Yes');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Nan Jing');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Bei Jing');
INSERT INTO SURVEY_OPTION (SURVEY_ID, CONTENT) VALUES (9,'Hang Kong');

INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"How time flies! 4 years!", 1, 3
);

INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"More than 10 years! I am really an old man.", 1, 1
);

INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"How I miss my first computer.", 5, 3
);

INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"Just 1 year.", 1, 2
);
INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"I like Cheng Du", 9, 4
);

INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (
"I am not a saver, 5 computers.", 5, 4
);

UPDATE SURVEY SET CREATED_TIME = '2013-01-03' WHERE ID = 1;
UPDATE SURVEY SET CREATED_TIME = '2012-12-03' WHERE ID = 2;



