package davenkin.opinions.domain;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Comment
{
    private Long id;
    private String content;
    private Long surveyId;
    private User user;
    private Timestamp createdTime;

    public Comment(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(Long surveyId)
    {
        this.surveyId = surveyId;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Timestamp getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime)
    {
        this.createdTime = createdTime;
    }
}
