package davenkin.opinions.persistence.dao;

import davenkin.opinions.persistence.DataAccessException;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSurveyTagDao extends AbstractDao implements SurveyTagDao
{
    public JdbcSurveyTagDao(DataSource dataSource)
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
