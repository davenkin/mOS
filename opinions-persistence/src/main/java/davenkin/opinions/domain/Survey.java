package davenkin.opinions.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Survey {
    private Long surveyId;
    private String surveyDescription;
    private List<SurveyCategory> surveyCategories;
    private Timestamp createdTime;
    private boolean canMultipleChecked;
    private List<SurveyOption> options;
    private List<SurveyComment> comments;
}
