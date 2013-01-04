package davenkin.opinions.domain;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 1/4/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public enum CategoryEnum {
    SCI("Science"),
    ECO("Economy"),
    CUL("Culture"),
    POL("Politics"),
    EDU("Education");


    private String name;
    CategoryEnum(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
