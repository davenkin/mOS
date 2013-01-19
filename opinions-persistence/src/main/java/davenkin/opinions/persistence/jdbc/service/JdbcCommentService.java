package davenkin.opinions.persistence.jdbc.service;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;
import davenkin.opinions.persistence.jdbc.dao.JdbcCommentDao;
import davenkin.opinions.persistence.service.CommentService;

import javax.sql.DataSource;
import java.util.ArrayList;
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
        List<Comment> surveys = new ArrayList<Comment>();
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
        List<Comment> comments = new ArrayList<Comment>();
        try
        {
            transactionManager.start();
            comments = commentDao.findCommentsFromUser(userId);
            transactionManager.commit();
            return comments;
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
        return comments;
    }

    @Override
    public void removeCommentFromSurvey(long commentId)
    {
        try
        {
            transactionManager.start();
            commentDao.removeComment(commentId);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

}
