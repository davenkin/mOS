package davenkin.opinions.web.controller;

import davenkin.opinions.domain.User;
import davenkin.opinions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegisterForm() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        userService.addUser(new User(userName, userEmail, userPassword));
        return "registerSuccess";
    }

    @Required
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
