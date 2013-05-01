Persistence layer for Opinions


This is the persistence layer project for Opinions, a survay web site.

userId vote should be anonymous

use Hibernate,contains serveice layer and DAO layer.

a userId can register/login using email address and password
an authenticated userId can create surveyId
an unauthenticated userId can view all the surveys and fulfill surveys and make commit



a userId has only one surveyId
a userId does not have any surveyId
a userId have multiple surveys


a surveyId has no comment
a surveyId has many comment from different userId

a tag used only by one surveyId
a tag used by many surveys

a category used by only one surveyId
a category used by many surveys

find all surveys:
SELECT ID * FROM SURVEY;


find the userId who created the surveyId:
SELECT * FROM USER WHERE ID = USER_ID;

find the category for the surveyId:
SELECT NAME FROM CATEGORY WHERE ID = USER_ID;

find the tags for the surveyId:
SELECT NAME FROM TAG WHERE ID IN (SELECT TAG_ID FROM SURVEY_TAG WHERE SURVEY_ID = ?);

find the comments for the surveyId:
SELECT * FROM COMMENT WHERE SURVEY_ID = ?

find the options for the surveyId:
SELECT * FROM SURVEY_OPTION WHERE SURVEY_ID = ?

find all surveys created by a specific userId
find all surveys with a specific tag
find all surveys with a specific category
for a surveyId, find it's related options
find all the tags that's related to a given surveyId
vote for one optionId of a surveyId
find all the surveys created during the last week
find all the surveys created during the last month
