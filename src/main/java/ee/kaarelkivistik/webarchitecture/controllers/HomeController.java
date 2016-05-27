package ee.kaarelkivistik.webarchitecture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kaarel on 27.05.16.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/service-requests";
    }

}
