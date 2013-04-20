package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.CommentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateCommentService implements CommentService {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long addCommentToSurvey(String content, User user, long surveyId) {
        Session session = sessionFactory.getCurrentSession();
        Survey survey = (Survey) session.load(Survey.class, surveyId);
        Comment comment = user.createComment(content, survey);
        survey.addComment(comment);
        Serializable save = session.save(comment);
        return (Long) save;
    }

    @Override
    @Transactional
    public List<Comment> getCommentsForSurvey(long surveyId) {
        Session session = sessionFactory.getCurrentSession();
        Survey survey = (Survey) session.load(Survey.class, surveyId);
        return survey.getComments();
    }

    @Override
    @Transactional
    public List<Comment> getCommentsFromUser(long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        return user.getComments();
    }

    @Override
    @Transactional
    public void removeCommentFromSurvey(long surveyId, long commentId) {
        Session session = sessionFactory.getCurrentSession();
        Survey survey = (Survey) session.load(Survey.class, surveyId);

        Comment comment = (Comment) session.load(Comment.class, commentId);
        comment.getUser().getComments().remove(comment);
        survey.getComments().remove(comment);

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
