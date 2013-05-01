package davenkin.opinions.domain;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Comment {
    private long id;
    private String content;
    private long surveyId;
    private long userId;
    private Timestamp createdTime;

    public Comment(String content, long userId, long surveyId) {
        this.content = content;
        this.surveyId = surveyId;
        this.userId = userId;
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (surveyId != comment.surveyId) return false;
        if (userId != comment.userId) return false;
        if (!content.equals(comment.content)) return false;
        if (!createdTime.equals(comment.createdTime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + (int) (surveyId ^ (surveyId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + createdTime.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

}
