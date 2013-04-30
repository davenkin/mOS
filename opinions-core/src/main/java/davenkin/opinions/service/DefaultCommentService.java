package davenkin.opinions.service;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.repository.SurveyRepository;
import davenkin.opinions.repository.UserRepository;
import davenkin.opinions.service.CommentService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultCommentService implements CommentService {
    private SurveyRepository surveyRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addCommentToSurvey(String content, long userId, long surveyId) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        User user = userRepository.getUser(userId);
        user.createComment(content, survey);
        surveyRepository.updateSurvey(survey);
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsForSurvey(long surveyId) {
        return surveyRepository.getSurvey(surveyId).getComments();
    }

    @Override
    @Transactional
    public List<Comment> getCommentsFromUser(long userId) {
        return userRepository.getUser(userId).getComments();
    }

    @Override
    @Transactional
    public void removeCommentFromSurvey(long surveyId, long commentId) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        Comment comment = survey.getComment(commentId);
        survey.removeComment(comment);
        User user = comment.getUser();
        user.removeComment(comment);
        userRepository.updateUser(user);
        surveyRepository.updateSurvey(survey);
    }


    @Required
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Required
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
