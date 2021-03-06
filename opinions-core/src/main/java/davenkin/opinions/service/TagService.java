package davenkin.opinions.service;

import java.util.Set;

public interface TagService
{
    public void addTagToSurvey(long surveyId, String tag);

    public void removeTagFromSurvey(long surveyId, String tag);

    public Set<String> getTagsForSurvey(long surveyId);

}
