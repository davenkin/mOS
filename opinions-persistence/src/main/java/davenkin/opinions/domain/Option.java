package davenkin.opinions.domain;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Option
{
    private Long id;
    private Long surveyId;
    private String option;
    private Long count;

    public Option(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(Long surveyId)
    {
        this.surveyId = surveyId;
    }

    public String getOption()
    {
        return option;
    }

    public void setOption(String option)
    {
        this.option = option;
    }

    public Long getCount()
    {
        return count;
    }

    public void setCount(Long count)
    {
        this.count = count;
    }
}
