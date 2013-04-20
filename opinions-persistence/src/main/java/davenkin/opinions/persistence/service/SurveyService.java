package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;

import java.util.List;
import java.util.Set;

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

    public void takeSurvey(Option option);

    public void takeSurvey(User user, Option option);

    public void removeSurvey(User user, long surveyId);

    public long addSurvey(User user, Survey survey);

}
