package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.*;
import ee.kaarelkivistik.webarchitecture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

/**
 * Created by kaarel on 26.05.16.
 */

@Controller
public class ServiceRequestController {

    public static final int SERVICE_REQUEST_IN_PROGRESS_ID = 3;

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

    @Autowired
    ServiceDeviceStatusTypeRepository serviceDeviceStatusTypeRepository;

    @InitBinder
    public void dataBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt", "createdBy");
    }

    /*@ModelAttribute
    public Iterable<ServiceRequestStatusType> serviceRequestStatusTypes() {
        return serviceRequestStatusTypeRepository.findAll();
    }

    @ModelAttribute
    public Iterable<ServiceOrderStatusType> serviceOrderStatusTypes() {
        return serviceOrderStatusTypeRepository.findAll();
    }

    @ModelAttribute
    public Iterable<ServiceDeviceStatusType> serviceDeviceStatusTypes() {
        return serviceDeviceStatusTypeRepository.findAll();
    }*/

    @RequestMapping("/service-requests")
    public String showList(Model model, Principal principal) {

        model.addAttribute("serviceRequests", serviceRequestRepository.findAll());

        return SERVICE_REQUESTS_TEMPLATE;
    }

    @RequestMapping("/service-requests/new")
    public String showForm(Model model) {

        model.addAttribute("requestStatusTypes", serviceRequestStatusTypeRepository.findAll());
        model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());

        model.addAttribute("creating", true);
        model.addAttribute("serviceRequest", new ServiceRequest());

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
    public String showFormForServiceRequest(@PathVariable Integer id, Model model) {

        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);
        ServiceOrder serviceOrder = serviceRequest.getServiceOrder();

        model.addAttribute("creating", false);
        model.addAttribute("serviceRequest", serviceRequest);
        model.addAttribute("serviceOrder", serviceOrder);

        model.addAttribute("requestStatusTypes", serviceRequestStatusTypeRepository.findAll());
        model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());
        model.addAttribute("serviceDeviceStatusTypes", serviceDeviceStatusTypeRepository.findAll());

        ServiceDevice serviceDevice = new ServiceDevice();
        serviceDevice.setServiceOrder(serviceOrder);
        model.addAttribute("serviceDevice", serviceDevice);

        ServicePart servicePart = new ServicePart();
        servicePart.setServiceOrder(serviceOrder);
        model.addAttribute(servicePart);

        return SERVICE_REQUEST_FORM_TEMPLATE;
    }

    @RequestMapping(value = "/service-requests/{id}", method = RequestMethod.POST)
    public String showResultForUpdate(@PathVariable Integer id, Model model, @Valid ServiceRequest newServiceRequest, BindingResult bindingResult) {

        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);

        if(bindingResult.hasErrors()) {
            ServiceOrder serviceOrder = serviceRequest.getServiceOrder();

            model.addAttribute("creating", false);
            model.addAttribute("serviceOrder", serviceOrder);

            model.addAttribute("requestStatusTypes", serviceRequestStatusTypeRepository.findAll());
            model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());
            model.addAttribute("serviceDeviceStatusTypes", serviceDeviceStatusTypeRepository.findAll());

            return SERVICE_REQUEST_FORM_TEMPLATE;
        } else {
            serviceRequest.setCustomer(newServiceRequest.getCustomer());
            serviceRequest.setStatusType(newServiceRequest.getStatusType());
            serviceRequest.setDescriptionByCustomer(newServiceRequest.getDescriptionByCustomer());
            serviceRequest.setDescriptionByEmployee(newServiceRequest.getDescriptionByEmployee());

            serviceRequestRepository.save(serviceRequest);

            return "redirect:/service-requests";
        }
    }

    @RequestMapping(value = "/service-requests/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findOne(id);
        ServiceOrder serviceOrder = serviceRequest.getServiceOrder();

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

        Date date = new Date();

        serviceOrder.setServiceRequest(serviceRequest);
        serviceOrder.setCreatedBy(employee);
        serviceOrder.setStatusChangedBy(employee);
        serviceOrder.setUpdatedBy(employee);
        serviceOrder.setCreatedAt(date);
        serviceOrder.setStatusChangedAt(date);
        serviceOrder.setUpdatedAt(date);
        serviceOrder.setPriceTotal(new BigDecimal(0));
        serviceOrder.setStatusType(serviceOrderStatusTypeRepository.findOne(1));

        serviceOrderRepository.save(serviceOrder);

        serviceRequest.setStatusType(serviceRequestStatusTypeRepository.findOne(SERVICE_REQUEST_IN_PROGRESS_ID));

        serviceRequestRepository.save(serviceRequest);

        return "redirect:/service-requests/" + serviceRequest.getId();
    }

}
