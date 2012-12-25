package davenkin.opinions.persistence;

import davenkin.opinions.domain.SurveyTag;
import davenkin.opinions.domain.Survey;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyService {
    public List<Survey> findSurveysByCategory(SurveyTag category);
    public Survey findSurveyById(Long surveyId);
    public void takeSurvey(Long surveyId, String surveyOption );
}
