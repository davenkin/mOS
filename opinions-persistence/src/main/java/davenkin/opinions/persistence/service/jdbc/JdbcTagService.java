package davenkin.opinions.persistence.service.jdbc;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.TagDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcTagDao;
import davenkin.opinions.persistence.service.TagService;

import javax.sql.DataSource;
import java.util.ArrayList;
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
        new JdbcTransactionTemplate(dataSource)
        {
            @Override
            protected void doJob() throws DataAccessException
            {
                tagDao.addTagForSurvey(surveyId, tag);
            }
        }.doJobInTransaction();

    }

    @Override
    public void removeTagFromSurvey(final long surveyId, final String tag)
    {
        new JdbcTransactionTemplate(dataSource)
        {
            @Override
            protected void doJob() throws Exception
            {
                tagDao.removeTagFromSurvey(surveyId, tag);
            }
        }.doJobInTransaction();
    }

    @Override
    public List<String> getTagsForSurvey(final long surveyId)
    {
        final List<String>[] tags = (List<String>[]) new ArrayList[1];

        new JdbcTransactionTemplate(dataSource)
        {
            @Override
            protected void doJob() throws Exception
            {
                tags[0] = tagDao.findTagsForSurvey(surveyId);
            }

        }.doJobInTransaction();
        return tags[0];
    }
}
