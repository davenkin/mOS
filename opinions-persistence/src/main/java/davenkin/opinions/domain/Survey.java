package davenkin.opinions.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Survey {
    private long id;
    private String content;
    private User creatingUser;
    private Timestamp createdTime;
    private boolean canMultipleChecked;
    private Category surveyCategory;
    private Set<String> surveyTags = new HashSet<String>();
    private List<Option> options = new ArrayList<Option>();
    private List<Comment> comments = new ArrayList<Comment>();

    public Survey(long id)
    {
        this.id = id;
    }

    public Survey() {

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

    public Category getSurveyCategory()
    {
        return surveyCategory;
    }

    public void setSurveyCategory(Category surveyCategory)
    {
        this.surveyCategory = surveyCategory;
    }

    public Set<String> getSurveyTags()
    {
        return surveyTags;
    }

    protected void setSurveyTags(Set<String> surveyTags)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addOptions(List<Option> options) {
        this.options.addAll(options);
    }

    public void addTag(String tag) {
        surveyTags.add(tag);
    }

    public void removeTag(String tag) {
        surveyTags.remove(tag);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
