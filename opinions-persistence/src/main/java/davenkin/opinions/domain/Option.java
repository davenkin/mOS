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
    private Survey survey;
    private String optionName;
    private long optionCount;

    public Option(){
        optionCount=0;
    }

    public Survey getSurvey()
    {
        return survey;
    }

    public void setSurvey(Survey survey)
    {
        this.survey = survey;
    }

    public String getOptionName()
    {
        return optionName;
    }

    public void setOptionName(String optionName)
    {
        this.optionName = optionName;
    }

    public long getOptionCount()
    {
        return optionCount;
    }

    public void setOptionCount(long optionCount)
    {
        this.optionCount = optionCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void vote() {
        this.optionCount++;
    }
}
