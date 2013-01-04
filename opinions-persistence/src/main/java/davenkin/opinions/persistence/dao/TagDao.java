package davenkin.opinions.persistence.dao;

import java.util.List;

public interface TagDao
{
    List<String> findTagsForSurvey(Long surveyId);
}
