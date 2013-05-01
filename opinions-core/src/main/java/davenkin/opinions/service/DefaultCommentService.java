package davenkin.opinions.service;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultCommentService implements CommentService {
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public long addComment(String content, long userId, long surveyId) {
        return commentRepository.addComment(new Comment(content, userId, surveyId));
    }

    @Override
    @Transactional
    public void removeComment(long commentId) {
        Comment comment = commentRepository.getComment(commentId);
        commentRepository.deleteComment(comment);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsBySurvey(long surveyId) {
        return commentRepository.getCommentBySurvey(surveyId);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByUser(long userId) {
        return commentRepository.getCommentByUser(userId);
    }

    @Required
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
