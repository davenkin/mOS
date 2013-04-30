package davenkin.opinions.service;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.User;

import java.util.List;

public interface CommentService {
    public void addCommentToSurvey(String content, long userId, long surveyId);

    public List<Comment> getCommentsForSurvey(long surveyId);

    public List<Comment> getCommentsFromUser(long userId);

    public void removeCommentFromSurvey(long surveyId, long commentId);

}
