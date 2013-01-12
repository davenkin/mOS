package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.Comment;

import java.util.List;

public interface CommentService
{
    public void addCommentToSurvey(Comment comment);

    public List<Comment> getCommentsForSurvey(long surveyId);

    public List<Comment> getCommentsFromUser(long userId);

    public void removeCommentFromSurvey(long surveyId, long commentId);

}
