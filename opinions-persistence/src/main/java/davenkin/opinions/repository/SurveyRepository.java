package davenkin.opinions.repository;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyRepository {
    public void saveSurvey(Survey survey);

    public Survey getSurvey(long id);

    public void delete(Survey survey);

    public List<Survey> findAllSurveys();

    public List<Survey> findSurveysByTag(String tag);

    public List<Survey> findSurveysByCategory(Category category);

    public List<Survey> findSurveysCreatedByUser(long userId);
}
