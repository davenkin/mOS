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
    private long id;
    private long surveyId;
    private String option;
    private long count;

    public Option(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public long getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(long surveyId)
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

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
    }
}
