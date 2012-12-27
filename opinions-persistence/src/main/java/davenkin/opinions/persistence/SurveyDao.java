package davenkin.opinions.persistence;

import davenkin.opinions.domain.*;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyDao {

    public Survey findSurveyById(Long surveyId);
    public List<Survey> findAllSurveys();
    public List<Survey> findSurveysCreatedByUser(Long userId);
    public List<Survey> findSurveysByCategory(String category);
    public List<Survey> findSurveysByTag(String tag);
    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate);

    public List<String> findTagsForSurvey(Long surveyId);
    public List<SurveyOption> findOptionsForSurvey(Long surveyId);
    public List<SurveyComment> findCommentsForSurvey(Long surveyId);
    public User findUserById(Long userId);

    public void takeSurvey(Long surveyId, Long optionId);

    public void removeSurvey(Long surveyId);
    public void removeComment(Long commentId);

}
