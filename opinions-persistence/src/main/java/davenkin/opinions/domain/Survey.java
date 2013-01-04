package davenkin.opinions.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Survey {
    private Long id;
    private String content;
    private User creatingUser;
    private Timestamp createdTime;
    private boolean canMultipleChecked;
    private String surveyCategory;
    private List<String> surveyTags;
    private List<Option> options;
    private List<Comment> comments;

    public Survey(Long id)
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

    public User getCreatingUser()
    {
        return creatingUser;
    }

    public void setCreatingUser(User creatingUser)
    {
        this.creatingUser = creatingUser;
    }

    public Timestamp getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime)
    {
        this.createdTime = createdTime;
    }

    public boolean isCanMultipleChecked()
    {
        return canMultipleChecked;
    }

    public void setCanMultipleChecked(boolean canMultipleChecked)
    {
        this.canMultipleChecked = canMultipleChecked;
    }

    public String getSurveyCategory()
    {
        return surveyCategory;
    }

    public void setSurveyCategory(String surveyCategory)
    {
        this.surveyCategory = surveyCategory;
    }

    public List<String> getSurveyTags()
    {
        return surveyTags;
    }

    public void setSurveyTags(List<String> surveyTags)
    {
        this.surveyTags = surveyTags;
    }

    public List<Option> getOptions()
    {
        return options;
    }

    public void setOptions(List<Option> options)
    {
        this.options = options;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
