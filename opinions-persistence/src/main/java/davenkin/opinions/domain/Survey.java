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
    private List<SurveyOption> surveyOptions;
    private List<SurveyComment> surveyComments;

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

    public List<SurveyOption> getSurveyOptions()
    {
        return surveyOptions;
    }

    public void setSurveyOptions(List<SurveyOption> surveyOptions)
    {
        this.surveyOptions = surveyOptions;
    }

    public List<SurveyComment> getSurveyComments()
    {
        return surveyComments;
    }

    public void setSurveyComments(List<SurveyComment> surveyComments)
    {
        this.surveyComments = surveyComments;
    }
}
