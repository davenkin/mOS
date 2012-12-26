package davenkin.opinions.persistence;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/26/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcSurveyDao  implements  SurveyDao{
    public Survey findSurveyById(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findAllSurveys() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedByUser(Long userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByCategory(String category) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByTag(String tag) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> findTagsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyOption> findOptionsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyComment> findCommentsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void takeSurvey(Long surveyId, Long optionId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
