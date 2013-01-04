package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Comment;

import java.util.List;

public interface CommentDao
{
    List<Comment> findCommentsForSurvey(Long surveyId);
    List<Comment> findCommentsFromUser(Long userId);
    public void removeComment(Long commentId);

}
