package davenkin.opinions.domain;


import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/20/13
 * Time: 8:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vote {
    private long id;
    private User user;
    private Option option;
    private Timestamp voteTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (!option.equals(vote.option)) return false;
        if (!user.equals(vote.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + option.hashCode();
        return result;
    }

    public Vote(User user, Option option) {
        this.user = user;
        this.option = option;
        voteTime = new Timestamp(System.currentTimeMillis());
    }

    protected Vote(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Timestamp getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Timestamp voteTime) {
        this.voteTime = voteTime;
    }
}
