package davenkin.opinions.persistence.service;

import java.util.List;

public interface TagService
{
    public void addTagToSurvey(long surveyId, String tag);

    public void removeTagFromSurvey(long surveyId, String tag);

    public List<String> getTagsForSurvey(long surveyId);

}
