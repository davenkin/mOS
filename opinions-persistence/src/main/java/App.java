import davenkin.opinions.domain.CategoryEnum;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 1/4/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {
    public static void main(String[] args){
        System.out.println(Thread.getAllStackTraces().keySet().size());
        String name = CategoryEnum.SCI.name();
        System.out.println(name);
        String s = CategoryEnum.SCI.toString();
        System.out.println(s);
    }

}
