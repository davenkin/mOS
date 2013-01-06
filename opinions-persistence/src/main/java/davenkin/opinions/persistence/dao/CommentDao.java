package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.DataAccessException;

import java.util.List;

public interface CommentDao
{
    List<Comment> findCommentsForSurvey(Long surveyId) throws DataAccessException;

    List<Comment> findCommentsFromUser(Long userId) throws DataAccessException;

    public void removeComment(Long commentId) throws DataAccessException;
    public void addCommentForSurvey(Long suveyId, Long userId, String content) throws DataAccessException;

}
