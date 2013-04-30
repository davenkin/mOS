package davenkin.opinions.service;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.repository.SurveyRepository;
import davenkin.opinions.service.TagService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultTagService implements TagService {

    private SurveyRepository surveyRepository;

    @Override
    @Transactional
    public void addTagToSurvey(long surveyId, String tag) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        survey.addTag(tag);
        surveyRepository.updateSurvey(survey);
    }

    @Override
    @Transactional
    public void removeTagFromSurvey(long surveyId, String tag) {
        Survey survey = surveyRepository.getSurvey(surveyId);
        survey.removeTag(tag);
        surveyRepository.updateSurvey(survey);
    }

    @Override
    @Transactional
    public Set<String> getTagsForSurvey(long surveyId) {
        return surveyRepository.getSurvey(surveyId).getSurveyTags();
    }

    @Required
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }
}
