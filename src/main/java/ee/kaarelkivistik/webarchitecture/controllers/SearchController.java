package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.Customer;
import ee.kaarelkivistik.webarchitecture.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by kaarel on 26.05.16.
 */

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("customer")
    public String showCustomerSearchForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "search/customer";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String showCustomerSearchResults(Model model, Customer customer) {
        List<Customer> results = null;

        if(customer.getName().length() > 0)
            results = customerRepository.findByNameLike("%" + customer.getName() + "%");

        model.addAttribute("results", results);

        return "search/customer";
    }

}
