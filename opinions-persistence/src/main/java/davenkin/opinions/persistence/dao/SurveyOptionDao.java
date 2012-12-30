package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.SurveyOption;

import java.util.List;

public interface SurveyOptionDao
{
    List<SurveyOption> findOptionsForSurvey(Long surveyId);
}
