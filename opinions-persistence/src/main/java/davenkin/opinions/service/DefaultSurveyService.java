package davenkin.opinions.service;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.repository.SurveyRepository;
import davenkin.opinions.repository.UserRepository;
import davenkin.opinions.service.SurveyService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/31/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultSurveyService implements SurveyService {
    private SurveyRepository surveyRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAllSurveys();
    }

    @Override
    @Transactional
    public List<Survey> getSurveysByTag(String tag) {
        return surveyRepository.findSurveysByTag(tag);
    }

    @Override
    @Transactional
    public List<Survey> getSurveysByCategory(Category category) {
        return surveyRepository.findSurveysByCategory(category);
    }

    @Override
    @Transactional
    public List<Survey> getSurveysCreatedByUser(long userId) {
        return surveyRepository.findSurveysCreatedByUser(userId);
    }

    @Override
    @Transactional
    public Survey getSurveyById(long surveyId) {
        return surveyRepository.getSurvey(surveyId);
    }

    @Override
    public void voteSurveyOption(long surveyId, long optionId) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        Option option = survey.getOption(optionId);
        option.vote();
        surveyRepository.updateSurvey(survey);
    }

    @Override
    public void voteSurveyOption(long userId, long surveyId, long optionId) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        User user = userRepository.getUser(userId);
        Option option = survey.getOption(optionId);
        user.vote(option);
        userRepository.updateUser(user);
        surveyRepository.updateSurvey(survey);
    }


    @Override
    @Transactional
    public void removeSurvey(long surveyId) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        surveyRepository.delete(survey);
    }


    @Override
    @Transactional
    public long addSurvey(Survey survey) {
        return surveyRepository.addSurvey(survey);
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
