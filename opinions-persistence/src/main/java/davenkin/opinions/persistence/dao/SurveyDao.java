package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.*;
import davenkin.opinions.persistence.DataAccessException;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyDao
{

    public Survey findSurveyById(Long surveyId) throws DataAccessException;

    public List<Survey> findAllSurveys() throws DataAccessException;

    public List<Survey> findSurveysCreatedByUser(Long userId) throws DataAccessException;

    public List<Survey> findSurveysByCategory(Category category) throws DataAccessException;

    public List<Survey> findSurveysByTag(String tag) throws DataAccessException;

    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate) throws DataAccessException;

}
