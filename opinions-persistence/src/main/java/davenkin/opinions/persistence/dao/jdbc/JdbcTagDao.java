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

    public List<String> findTagsForSurvey(Long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT TAG_NAME FROM SURVEY_TAG  WHERE SURVEY_ID = ?", new Object[]{surveyId}, String.class);
    }

    @Override
    public void addTagForSurvey(long surveyId, String tagName) throws DataAccessException
    {
        Integer existingId = jdbcTemplate.queryForObject("SELECT ID FROM SURVEY WHERE ID = ?", new Object[]{surveyId}, Integer.class);
        if (null == existingId)
        {
            throw new DataAccessException("User[" + surveyId + "] does not exist.");
        }
        List<String> existingTag = jdbcTemplate.queryForList("SELECT TAG_NAME FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{surveyId}, String.class);
        if (!existingTag.contains(tagName))
        {
            addTag(tagName);
            jdbcTemplate.update("INSERT INTO SURVEY_TAG VALUES (?, ?)", new Object[]{surveyId, tagName});
        }
    }

    @Override
    public void addTag(String name) throws DataAccessException
    {
        String existingName = jdbcTemplate.queryForObject("SELECT NAME FROM TAG WHERE NAME = ?", new Object[]{name}, String.class);
        if (existingName == null)
        {
            jdbcTemplate.update("INSERT INTO TAG VALUES (?)", new Object[]{name});
        }
    }

    @Override
    public List<String> findAllTags() throws DataAccessException
    {
      return  jdbcTemplate.queryForList("SELECT NAME FROM TAG", null, String.class);
    }

    @Override
    public void removeTag(String tagName) throws DataAccessException
    {
        jdbcTemplate.update("DELETE FROM SURVEY_TAG WHERE TAG_NAME = ?", new Object[]{tagName});
        jdbcTemplate.update("DELETE FROM TAG WHERE NAME = ?", new Object[]{tagName});
    }


}
