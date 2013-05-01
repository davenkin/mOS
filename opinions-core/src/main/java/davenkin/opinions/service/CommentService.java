package davenkin.opinions.service;

import davenkin.opinions.domain.Comment;

import java.util.List;

public interface CommentService {

    public long addComment(String content, long userId, long surveyId);

    public void removeComment(long commentId);

    public List<Comment> getCommentsBySurvey(long surveyId);

    public List<Comment> getCommentsByUser(long userId);

}
