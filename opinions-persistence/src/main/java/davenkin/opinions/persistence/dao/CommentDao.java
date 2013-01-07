package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.DataAccessException;

import java.util.List;

public interface CommentDao
{
    List<Comment> findCommentsForSurvey(long surveyId) throws DataAccessException;

    List<Comment> findCommentsFromUser(long userId) throws DataAccessException;

    public void removeComment(long commentId) throws DataAccessException;
    public void addCommentForSurvey(long surveyId, long userId, String content) throws DataAccessException;

}
