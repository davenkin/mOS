package davenkin.opinions.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/6/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/register")
public class RegisterController {
        @RequestMapping(method = RequestMethod.GET)
        public String register() {
            return "register";

        }
}
