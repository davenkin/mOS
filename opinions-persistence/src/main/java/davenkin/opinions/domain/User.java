package davenkin.opinions.domain;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
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
    private Set<Survey> surveys = new HashSet<Survey>();
    private List<Comment> comments = new ArrayList<Comment>();
    private Set<Vote> votes = new HashSet<Vote>();

    protected User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.registerTime = new Timestamp(System.currentTimeMillis());
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

    public Set<Survey> getSurveys() {
        return surveys;
    }

    protected void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
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

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
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


    public void addSurvey(Survey survey) {
        survey.setCreatingUser(this);
        surveys.add(survey);
    }

    public void voteOption(Option option) {
        Vote vote = new Vote(this, option);
        if (!votes.contains(vote)) {
            option.vote();
            votes.add(vote);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }
}
