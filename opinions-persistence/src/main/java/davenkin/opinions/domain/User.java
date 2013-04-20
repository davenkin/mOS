package davenkin.opinions.domain;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Option> options = new ArrayList<Option>();

    protected User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
    }

    protected void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
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

    public Survey createSurvey(String content, boolean multipleChecked, Category category, List<String> optionsNames, Set<String> tags) {
        Survey survey = new Survey(content, this, multipleChecked, category, tags, optionsNames);
        surveys.add(survey);
        return survey;
    }

    public Comment createComment(String content, Survey survey) {
        Comment comment = new Comment(content, survey, this);
        comments.add(comment);
        return comment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    protected void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public List<Option> getOptions() {
        return options;
    }

    protected void setOptions(List<Option> options) {
        this.options = options;
    }

    public void takeSurveyOption(Option option) {
        option.vote();
        options.add(option);
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }
}
