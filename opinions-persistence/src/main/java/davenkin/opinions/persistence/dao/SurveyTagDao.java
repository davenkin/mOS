package davenkin.opinions.persistence.dao;

import java.util.List;

public interface SurveyTagDao
{
    List<String> findTagsForSurvey(Long surveyId);
}
