package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.TagDao;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTagDao extends AbstractJdbcDao implements TagDao
{
    public JdbcTagDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<String> findTagsForSurvey(Long surveyId)
    {
        try
        {
            return jdbcTemplate.queryForList("SELECT TAG.NAME FROM SURVEY_TAG LEFT JOIN TAG ON SURVEY_TAG.TAG_ID = TAG.ID WHERE SURVEY_TAG.SURVEY_ID = ?", new Object[]{surveyId}, String.class);
        } catch (DataAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
