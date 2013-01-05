package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/27/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SurveyService {
    public List<Survey> getAllSurveys();
    public List<Survey> getSurveysByTag(String tag);
    public List<Survey> getSurveysByCategory(String category);
    public Survey getSurveyById(int surveyId);

    public List<Comment> getCommentsForSurvey(int surveyId);

    public void addSurvey(String content, Category category, boolean isMultiple, List<String> options);

    public void addCommentToSurvey(int surveyId, String comment);

    public void takeSurvey(int surveyId, int optionId);
}
