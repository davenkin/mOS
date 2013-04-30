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

    public Survey(String content, boolean canMultipleChecked, Category surveyCategory, List<String> optionNames, Set<String> surveyTags) {
        this.content = content;
        this.canMultipleChecked = canMultipleChecked;
        this.surveyCategory = surveyCategory;
        this.surveyTags = surveyTags;
        this.options = createOptions(optionNames);
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }

    public String getContent() {
        return content;
    }


    protected void setCreatingUser(User creatingUser) {
        this.creatingUser = creatingUser;
    }



    public Category getSurveyCategory() {
        return surveyCategory;
    }



    public Set<String> getSurveyTags() {
        return surveyTags;
    }


    public List<Option> getOptions() {
        return options;
    }


    public List<Comment> getComments() {
        return comments;
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

    private List<Option> createOptions(List<String> optionNames) {
        ArrayList<Option> options = new ArrayList<Option>();
        for (String optionName : optionNames) {
            options.add(new Option(this, optionName));
        }
        return options;
    }

    protected Survey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (!content.equals(survey.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
