package ee.kaarelkivistik.webarchitecture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kaarel on 25.05.16.
 */

@Controller
public class LoginController {

    @RequestMapping("login")
    public String showLoginForm() {
        return "login";
    }

}
