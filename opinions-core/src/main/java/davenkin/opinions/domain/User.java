package davenkin.opinions.domain;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.HashSet;
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

    private Set<Vote> votes = new HashSet<Vote>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = DigestUtils.md5Hex(password);
        this.registerTime = new Timestamp(System.currentTimeMillis());
    }

    public void vote(Option option) {
        Vote vote = new Vote(this.getId(), option.getId());
        if (!votes.contains(vote)) {
            option.vote();
            votes.add(vote);
        }
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Vote> getVotes() {
        return votes;
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

    public long getId() {
        return id;
    }
}
