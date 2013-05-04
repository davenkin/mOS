package davenkin.opinions.repository.hibernate;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.repository.CommentRepository;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/1/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateCommentRepository extends HibernateRepository implements CommentRepository {
    @Override
    public Comment getComment(long id) {
        return (Comment) getCurrentSession().load(Comment.class, id);
    }

    @Override
    public long addComment(Comment comment) {
        Serializable save = getCurrentSession().save(comment);
        return (Long) save;
    }

    @Override
    public void deleteComment(Comment comment) {
        getCurrentSession().delete(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return getCurrentSession().createQuery("from Comment").list();
    }

    @Override
    public List<Comment> getCommentByUser(long userId) {
        Query query = getCurrentSession().createQuery("from Comment as comment where comment.userId = :userId");
        query.setLong("userId", userId);
        return query.list();
    }

    @Override
    public List<Comment> getCommentBySurvey(long surveyId) {
        Query query = getCurrentSession().createQuery("from Comment as comment where comment.surveyId=:surveyId");
        query.setLong("surveyId", surveyId);
        return query.list();
    }

    @Override
    public void removeCommentForSurvey(long surveyId) {
        Query query = getCurrentSession().createQuery("delete Comment as comment where comment.surveyId=:surveyId");
        query.setLong("surveyId", surveyId);
        query.executeUpdate();
    }
}
