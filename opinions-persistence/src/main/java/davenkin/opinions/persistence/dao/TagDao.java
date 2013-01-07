package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.DataAccessException;

import java.util.List;

public interface TagDao
{
    public List<String> findTagsForSurvey(long surveyId) throws DataAccessException;

    public void addTagForSurvey(long surveyId, String tagName) throws DataAccessException;

    public List<String> findAllTags() throws DataAccessException;
    
    public void removeTag(String tagName) throws DataAccessException;
}
