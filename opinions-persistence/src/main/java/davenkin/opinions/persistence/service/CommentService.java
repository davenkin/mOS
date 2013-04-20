package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.User;

import java.util.List;

public interface CommentService {
    public long addCommentToSurvey(String content, User user, long surveyId);

    public List<Comment> getCommentsForSurvey(long surveyId);

    public List<Comment> getCommentsFromUser(long userId);

    public void removeCommentFromSurvey(long surveyId,
                                        long commentId);

}
