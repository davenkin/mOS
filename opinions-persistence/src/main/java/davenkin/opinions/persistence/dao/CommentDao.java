package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.SurveyComment;

import java.util.List;

public interface CommentDao
{
    List<SurveyComment> findCommentsForSurvey(Long surveyId);
    List<SurveyComment> findCommentsFromUser(Long userId);
    public void removeComment(Long commentId);

}
