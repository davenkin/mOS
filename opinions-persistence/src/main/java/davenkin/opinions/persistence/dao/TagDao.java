package davenkin.opinions.persistence.dao;

import davenkin.opinions.persistence.DataAccessException;

import java.util.List;

public interface TagDao
{
    public List<String> findTagsForSurvey(Long surveyId) throws DataAccessException;

    public void addTagForSurvey(long surveyId, String tagName) throws DataAccessException;

    public void addTag(String name) throws DataAccessException;
    
    public List<String> findAllTags() throws DataAccessException;
    
    public void removeTag(String tagName) throws DataAccessException;
}
