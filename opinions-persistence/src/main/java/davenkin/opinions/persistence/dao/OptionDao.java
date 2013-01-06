package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Option;
import davenkin.opinions.persistence.DataAccessException;

import java.util.List;

public interface OptionDao
{
    public List<Option> findOptionsForSurvey(Long surveyId) throws DataAccessException;

    public void increaseOptionCount(Long id) throws DataAccessException;

    public Option findOption(Long id) throws DataAccessException;
}
