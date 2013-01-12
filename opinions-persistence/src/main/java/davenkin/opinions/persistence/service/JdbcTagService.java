package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.TagDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcTagDao;
import davenkin.opinions.persistence.service.jdbc.JdbcTransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTagService implements TagService
{
    private TagDao tagDao;
    private DataSource dataSource;


    public JdbcTagService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.tagDao = new JdbcTagDao(dataSource);
    }

    @Override
    public void addTagToSurvey(final long surveyId, final String tag)
    {
        JdbcTransactionTemplate template = new JdbcTransactionTemplate(dataSource)
        {
            @Override
            protected void doJob() throws DataAccessException
            {
                tagDao.addTagForSurvey(surveyId, tag);
            }
        };
        template.doJobInTransaction();

    }

    @Override
    public void removeTagFromSurvey(long surveyId, String tag)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getTagsForSurvey(long surveyId)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
