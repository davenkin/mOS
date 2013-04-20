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
    private Survey survey;
    private User user;
    private Timestamp createdTime;

    protected Comment(String content, Survey survey, User user) {
        this.content = content;
        this.survey = survey;
        this.user = user;
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }

    public String getContent() {
        return content;
    }

    public Survey getSurvey() {
        return survey;
    }

    public User getUser() {
        return user;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public long getId() {
        return id;
    }

    protected void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    protected void setId(long id) {
        this.id = id;
    }

    protected Comment() {
    }

    protected void setContent(String content) {
        this.content = content;
    }

    protected void setSurvey(Survey survey) {
        this.survey = survey;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!content.equals(comment.content)) return false;
        if (!createdTime.equals(comment.createdTime)) return false;
        if (!survey.equals(comment.survey)) return false;
        if (!user.equals(comment.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + survey.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + createdTime.hashCode();
        return result;
    }
}
