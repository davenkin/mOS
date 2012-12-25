package davenkin.opinions.domain;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SurveyComment {
    private Long id;
    private String content;
    private Long surveyId;
    private User commentUser;
    private Timestamp createdTime;
}
