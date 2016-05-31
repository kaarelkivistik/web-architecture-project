package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.dao.DeviceTypeDAO;
import ee.kaarelkivistik.webarchitecture.models.Customer;
import ee.kaarelkivistik.webarchitecture.models.Device;
import ee.kaarelkivistik.webarchitecture.repository.CustomerRepository;
import ee.kaarelkivistik.webarchitecture.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kaarel on 26.05.16.
 */

@Controller
@RequestMapping("/search")
public class SearchController {

    public static final int PAGE_SIZE = 20;

    @Autowired
    DeviceTypeDAO deviceTypeDAO;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @RequestMapping("customer")
    public String showCustomerSearchForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "search/customer";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String showCustomerSearchResults(Model model, Customer customer) {
        List<Customer> results = null;

        if(customer.getName().length() > 0)
            results = customerRepository.findByNameContainingIgnoreCase(customer.getName());

        model.addAttribute("results", results);

        return "search/customer";
    }

    @RequestMapping("device")
    public String showDeviceSearchForm(Model model) {
        model.addAttribute("deviceTypes", deviceTypeDAO.findAll());
        model.addAttribute("device", new Device());
        model.addAttribute("newDevice", new Device());

        model.addAttribute("results", deviceRepository.findAll(new PageRequest(0, PAGE_SIZE)));

        return "search/device";
    }

    @RequestMapping(value = "device", method = RequestMethod.POST)
    public String showDeviceSearchResults(Model model, Device device) {
        model.addAttribute("deviceTypes", deviceTypeDAO.findAll());
        model.addAttribute("newDevice", new Device());

        Page<Device> results = null;

        PageRequest pageRequest = new PageRequest(0, PAGE_SIZE);

        if(device.getName().length() > 0)
            results = deviceRepository.findByNameContainingIgnoreCase(device.getName(), pageRequest);
        else {
            results = deviceRepository.findAll(pageRequest);
        }

        model.addAttribute("results", results);

        return "search/device";
    }

    @RequestMapping(value = "device/create", method = RequestMethod.POST)
    public String insertNewDevice(Model model, @Valid @ModelAttribute("newDevice") Device newDevice, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("deviceTypes", deviceTypeDAO.findAll());
            model.addAttribute("deviceCreatedSuccessfully", false);
            model.addAttribute("device", new Device());
        } else {
            deviceRepository.save(newDevice);

            model.addAttribute("deviceCreatedSuccessfully", true);
            model.addAttribute("device", newDevice);
        }

        return "search/device";
    }

}
