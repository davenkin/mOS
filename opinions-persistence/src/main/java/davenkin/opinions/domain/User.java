package davenkin.opinions.domain;

import java.sql.Timestamp;
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
    private Set<Survey> surveys;

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

    public Set<Survey> getSurveys() {
        return surveys;
    }

    protected void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
    }
}
