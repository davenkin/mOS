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
    private Long id;
    private String content;
    private User creatingUser;
    private Timestamp createdTime;
    private boolean canMultipleChecked;
    private SurveyCategory surveyCategory;
    private List<SurveyTag> surveyTags;
    private List<SurveyOptionCount> surveyOptions;
    private List<SurveyComment> surveyComments;
}
