package davenkin.opinions.domain;

import java.util.List;

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

    protected Option(){

        optionCount=0;
    }

    protected Option(Survey survey, String optionName){
        this.survey=survey;
        this.optionName=optionName;
        optionCount=0;
    }


    public long getOptionCount()
    {
        return optionCount;
    }



    public void vote() {
        this.optionCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (!optionName.equals(option.optionName)) return false;
        if (!survey.equals(option.survey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = survey.hashCode();
        result = 31 * result + optionName.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }
}
