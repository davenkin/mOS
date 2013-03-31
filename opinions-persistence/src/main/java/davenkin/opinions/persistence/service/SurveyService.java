package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/27/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyService
{
    public List<Survey> getAllSurveys();

    public List<Survey> getSurveysByTag(String tag);

    public List<Survey> getSurveysByCategory(Category category);

    public List<Survey> getSurveysCreatedByUser(long userId);

    public Survey getSurveyById(long surveyId);

    public long addSurvey(Survey survey);

    public void takeSurvey(long surveyId, long optionId);

    public void removeSurvey(long surveyId);
}
