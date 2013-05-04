package davenkin.opinions.repository;

import davenkin.opinions.domain.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentRepository {
    public Comment getComment(long id);

    public long addComment(Comment comment);

    public void deleteComment(Comment comment);

    public List<Comment> getAllComments();

    public List<Comment> getCommentByUser(long userId);

    public List<Comment> getCommentBySurvey(long surveyId);

    public void removeCommentForSurvey(long surveyId);
}
