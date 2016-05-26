package ee.kaarelkivistik.webarchitecture.controllers;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by kaarel on 25.05.16.
 */

@Controller
public class TestController {

    public static final String TEST_TEMPLATE = "test";
    public static final String TEST_ROUTE = "/test";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new FooBarValidator());
    }

    @RequestMapping(TEST_ROUTE)
    public String showHome(Model model) {

        model.addAttribute("fooBar", new FooBar());

        return TEST_TEMPLATE;
    }

    @RequestMapping(value = TEST_ROUTE, method = RequestMethod.POST)
    public String showHomeResult(@Valid FooBar fooBar, BindingResult bindingResult) {
        System.out.println(fooBar.toString());

        System.out.println(bindingResult.toString());

        return TEST_TEMPLATE;
    }

}

class FooBar {

    private String a;

    private Integer b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "FooBar{" +
                "a='" + a + '\'' +
                ", b=" + b +
                '}';
    }
}

class FooBarValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return FooBar.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FooBar fooBar = (FooBar) o;

        if(fooBar.getA().equals("foobar"))
            errors.rejectValue("a", "isFoobar");
    }
}