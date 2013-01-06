package davenkin.opinions.domain;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 1/4/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public enum CategoryEnum
{
    SCIENCE("001"),
    ECONOMY("002"),
    CULTURE("003"),
    POLITICS("004"),
    EDUCATION("005");

    private String code;

    CategoryEnum(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

}
