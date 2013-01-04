package davenkin.opinions.persistence;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/27/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyService {
    public List<User> getAllUsers();
    public List<Survey> getAllSurveys();
    public List<Survey> getSurveysByTag(String tag);
    public Survey getSurveyById(long surveyId);

    public List<Comment> getCommentsForSurvey(long surveyId);

    public void createSurvey(String content, Category category, boolean isMultiple, List<String> options);

    public void addCommentToSurvey(long surveyId, String comment);

    public void takeSurvey(long surveyId,String option);
}
