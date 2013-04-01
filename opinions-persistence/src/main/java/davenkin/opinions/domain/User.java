package davenkin.opinions.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private Timestamp registerTime;
    private List<Survey> surveys = new ArrayList<Survey>();

    private User() {
    }

    public User(String name, String email, String password, Timestamp registerTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registerTime = registerTime;
    }

    private void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    protected void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void addSurvey(Survey survey) {
     surveys.add(survey);
    }

    public Survey createSurvey(String content, boolean multipleChecked, Category category, List<String> optionsNames) {
        Survey survey = new Survey();
        survey.setContent(content);
        survey.setCanMultipleChecked(multipleChecked);
        survey.setSurveyCategory(category);
        survey.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        survey.setCreatingUser(this);
        ArrayList<Option> options = new ArrayList<Option>();
        for(String optionName: optionsNames)
        {
            Option option = new Option();
            option.setOptionName(optionName);
            option.setSurvey(survey);
            options.add(option);
        }
        survey.addOptions(options);
        surveys.add(survey);
        return survey;
    }

    public Comment createComment(String content, Survey survey) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        comment.setSurvey(survey);
        comment.setUser(this);
        return comment;
    }
}
