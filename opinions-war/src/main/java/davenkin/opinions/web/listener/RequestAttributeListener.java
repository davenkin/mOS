package davenkin.opinions.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/6/13
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestAttributeListener implements ServletRequestAttributeListener {
    private static Logger logger= LoggerFactory.getLogger(RequestAttributeListener.class);

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        logger.info("Attribute added: name=" + srae.getName() + ", attribute=" + srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        logger.info("Attribute removed: name="+srae.getName()+", attribute="+srae.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
logger.info("Attribute replaced: name= "+srae.getName()+", attribute="+srae.getValue());    }
}
