package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.*;
import ee.kaarelkivistik.webarchitecture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

/**
 * Created by kaarel on 26.05.16.
 */

@Controller
public class ServiceRequestController {

    public static final String SERVICE_REQUEST_FORM_TEMPLATE = "service-request-form";
    public static final String SERVICE_REQUESTS_TEMPLATE = "service-requests";

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    ServiceOrderStatusTypeRepository serviceOrderStatusTypeRepository;

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ServiceRequestStatusTypeRepository serviceRequestStatusTypeRepository;

    @InitBinder
    public void dataBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt", "createdBy");
    }

    @RequestMapping("/service-requests")
    public String showList(Model model, Principal principal) {

        model.addAttribute("serviceRequests", serviceRequestRepository.findAll());

        return SERVICE_REQUESTS_TEMPLATE;
    }

    @RequestMapping("/service-requests/new")
    public String showForm(Model model, Principal principal) {

        model.addAttribute("creating", true);
        model.addAttribute("serviceRequest", new ServiceRequest());
        model.addAttribute("statusTypes", serviceRequestStatusTypeRepository.findAll());

        return SERVICE_REQUEST_FORM_TEMPLATE;
    }

    @RequestMapping(value = "/service-requests/new", method = RequestMethod.POST)
    public String showResultForCreate(Model model, @Valid ServiceRequest serviceRequest, BindingResult bindingResult, Principal principal) {

        serviceRequest.setCreatedBy(employeeRepository.findByName(principal.getName()));
        serviceRequest.setCreatedAt(new Date());

        if(bindingResult.hasErrors())
            return SERVICE_REQUEST_FORM_TEMPLATE;
        else {
            serviceRequestRepository.save(serviceRequest);

            return "redirect:/service-requests/" + serviceRequest.getId();
        }
    }

    @RequestMapping("/service-requests/{id}")
    public String showFormForServiceRequest(@PathVariable Integer id, Model model, Principal principal) {

        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);

        model.addAttribute("creating", false);
        model.addAttribute("serviceRequest", serviceRequest);
        model.addAttribute("serviceOrder",  serviceOrderRepository.findByServiceRequest(serviceRequest));
        model.addAttribute("statusTypes", serviceRequestStatusTypeRepository.findAll());

        return SERVICE_REQUEST_FORM_TEMPLATE;
    }

    @RequestMapping(value = "/service-requests/{id}", method = RequestMethod.POST)
    public String showResultForUpdate(@PathVariable Integer id, Model model, @Valid ServiceRequest serviceRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return SERVICE_REQUEST_FORM_TEMPLATE;
        else {
            ServiceRequest currentServiceRequest = serviceRequestRepository.findOne(id);

            currentServiceRequest.setCustomer(serviceRequest.getCustomer());
            currentServiceRequest.setStatusType(serviceRequest.getStatusType());
            currentServiceRequest.setDescriptionByCustomer(serviceRequest.getDescriptionByCustomer());
            currentServiceRequest.setDescriptionByEmployee(serviceRequest.getDescriptionByEmployee());

            serviceRequestRepository.save(currentServiceRequest);

            return "redirect:/service-requests";
        }
    }

    @RequestMapping(value = "/service-requests/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);
        ServiceOrder serviceOrder = serviceOrderRepository.findByServiceRequest(serviceRequest);

        if(serviceOrder != null)
            serviceOrderRepository.delete(serviceOrder);

        serviceRequestRepository.delete(serviceRequest);

        return "redirect:/service-requests";
    }

    @RequestMapping(value = "/service-requests/{id}/create-service-order", method = RequestMethod.POST)
    public String createServiceOrder(@PathVariable Integer id, Principal principal) {
        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);

        Employee employee = employeeRepository.findByName(principal.getName());

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setServiceRequest(serviceRequest);
        serviceOrder.setCreatedBy(employee);
        serviceOrder.setStatusChangedBy(employee);
        serviceOrder.setCreatedAt(new Date());
        serviceOrder.setStatusChangedAt(new Date());
        serviceOrder.setPriceTotal(new BigDecimal(0));
        serviceOrder.setStatusType(serviceOrderStatusTypeRepository.findOne(1));

        serviceOrderRepository.save(serviceOrder);

        return "redirect:/service-requests/" + serviceRequest.getId();
    }

}
