package davenkin.opinions.persistence.service.jdbc;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;
import davenkin.opinions.persistence.jdbc.dao.JdbcCommentDao;
import davenkin.opinions.persistence.service.CommentService;

import javax.sql.DataSource;
import java.util.List;

public class JdbcCommentService implements CommentService
{
    private CommentDao commentDao;
    private JdbcTransactionManager transactionManager;

    public JdbcCommentService(DataSource dataSource)
    {
        this.commentDao = new JdbcCommentDao(dataSource);
        transactionManager = new JdbcTransactionManager(dataSource);
    }

    @Override
    public void addCommentToSurvey(String content, long surveyId, long userId)
    {
        try
        {
            transactionManager.start();
            commentDao.addCommentForSurvey(surveyId, userId, content);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

    @Override
    public List<Comment> getCommentsForSurvey(long surveyId)
    {
        List<Comment> surveys = null;
        try
        {
            transactionManager.start();
            surveys = commentDao.findCommentsForSurvey(surveyId);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
        return surveys;
    }

    @Override
    public List<Comment> getCommentsFromUser(long userId)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeCommentFromSurvey(long surveyId, long commentId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
