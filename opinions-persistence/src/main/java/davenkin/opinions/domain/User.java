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

    private List<Comment> comments = new ArrayList<Comment>();
    private Set<Vote> votes = new HashSet<Vote>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.registerTime = new Timestamp(System.currentTimeMillis());
    }

    public void vote(Option option) {
        Vote vote = new Vote(this, option);
        if (!votes.contains(vote)) {
            option.vote();
            votes.add(vote);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Comment createComment(String content, Survey survey) {
        Comment comment = new Comment(content, survey, this);
        comments.add(comment);
        survey.addComment(comment);
        return comment;

    }

    public Survey createSurvey(String content, boolean canMultipleChecked, Category surveyCategory, List<String> optionNames, Set<String> surveyTags) {
        return new Survey(this,content, canMultipleChecked, surveyCategory, optionNames, surveyTags);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public Set<Vote> getVotes() {
        return votes;
    }
}
