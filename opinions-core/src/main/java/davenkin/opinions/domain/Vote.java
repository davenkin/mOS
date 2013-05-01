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
    private long userId;
    private long optionId;
    private Timestamp voteTime;

    public Vote(long userId, long optionId) {
        this.userId = userId;
        this.optionId = optionId;
        voteTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (optionId != vote.optionId) return false;
        if (userId != vote.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (optionId ^ (optionId >>> 32));
        return result;
    }
}
