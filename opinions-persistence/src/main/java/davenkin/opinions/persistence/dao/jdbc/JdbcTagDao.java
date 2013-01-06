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

    @Override
    public List<String> findTagsForSurvey(Long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT TAG_NAME FROM SURVEY_TAG  WHERE SURVEY_ID = ?", new Object[]{surveyId}, String.class);
    }

    @Override
    public void addTagForSurvey(Long surveyId, String tagName) throws DataAccessException
    {
        if (!surveyExist(surveyId))
        {
            throw new DataAccessException("User[" + surveyId + "] does not exist.");
        }

        if (!tagReferenceExist(tagName, surveyId))
        {
            tryAddTag(tagName);
            jdbcTemplate.update("INSERT INTO SURVEY_TAG VALUES (?, ?)", new Object[]{surveyId, tagName});
        }
    }

    @Override
    public List<String> findAllTags() throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT NAME FROM TAG", null, String.class);
    }

    @Override
    public void removeTag(String tagName) throws DataAccessException
    {
        removeTagReference(tagName);
        jdbcTemplate.update("DELETE FROM TAG WHERE NAME = ?", new Object[]{tagName});
    }

    private void removeTagReference(String tagName) throws DataAccessException
    {
        jdbcTemplate.update("DELETE FROM SURVEY_TAG WHERE TAG_NAME = ?", new Object[]{tagName});
    }

    private boolean tagReferenceExist(String tagName, long surveyId) throws DataAccessException
    {
        List<String> existingTags = jdbcTemplate.queryForList("SELECT TAG_NAME FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{surveyId}, String.class);
        return existingTags.contains(tagName);

    }

    private boolean tagExist(String name) throws DataAccessException
    {
        String existingName = jdbcTemplate.queryForObject("SELECT NAME FROM TAG WHERE NAME = ?", new Object[]{name}, String.class);
        return null != existingName;
    }

    private void tryAddTag(String name) throws DataAccessException
    {
        if (!tagExist(name))
        {
            jdbcTemplate.update("INSERT INTO TAG VALUES (?)", new Object[]{name});
        }
    }

    private boolean surveyExist(long surveyId) throws DataAccessException
    {
        Integer existingId = jdbcTemplate.queryForObject("SELECT ID FROM SURVEY WHERE ID = ?", new Object[]{surveyId}, Integer.class);
        return null != existingId;

    }

}
