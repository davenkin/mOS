package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.DataAccessException;

import java.util.List;

public interface OptionDao
{
    public List<Option> findOptionsForSurvey(long surveyId) throws DataAccessException;

    public void increaseOptionCount(long id) throws DataAccessException;

    public Option findOption(long id) throws DataAccessException;
}
