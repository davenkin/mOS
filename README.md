Persistence layer for Opinions


This is the persistence layer project for Opinions, a survay web site.

user vote should be anonymous

use Hibernate,contains serveice layer and DAO layer.

a user can register/login using email address and password
an authenticated user can create survey
an unauthenticated user can view all the surveys and fulfill surveys and make commit



a user has only one survey
a user does not have any survey
a user have multiple surveys


a survey has no comment
a survey has many comment from different user

a tag used only by one survey
a tag used by many surveys

a category used by only one survey
a category used by many surveys

find all surveys
find all surveys created by a specific user
find all surveys with a specific tag
find all surveys with a specific category
for a survey, find it's related options
find all the tags that's related to a given survey
vote for one option of a survey
find all the surveys created during the last week
find all the surveys created during the last month
