package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Option;

import java.util.List;

public interface OptionDao
{
    List<Option> findOptionsForSurvey(Long surveyId);
}
