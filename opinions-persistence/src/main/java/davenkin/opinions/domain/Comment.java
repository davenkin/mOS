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
    private long id;
    private String content;
    private long surveyId;
    private User user;
    private Timestamp createdTime;

    public Comment()
    {
    }

    public Comment(long id)
    {
        this.id = id;
    }

    public long getId()
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

    public long getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(long surveyId)
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
