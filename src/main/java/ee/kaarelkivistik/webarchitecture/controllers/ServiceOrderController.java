package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.ServiceDevice;
import ee.kaarelkivistik.webarchitecture.models.ServiceOrder;
import ee.kaarelkivistik.webarchitecture.models.ServicePart;
import ee.kaarelkivistik.webarchitecture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kaarel on 27.05.16.
 */

@Controller
public class ServiceOrderController {

    public static final String SERVICE_ORDER_FORM_TEMPLATE = "service-order-form";
    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    ServiceDeviceRepository serviceDeviceRepository;

    @Autowired
    ServiceOrderStatusTypeRepository serviceOrderStatusTypeRepository;

    @Autowired
    ServiceDeviceStatusTypeRepository serviceDeviceStatusTypeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ServicePartRepository servicePartRepository;

    @InitBinder
    public void dataBinder(WebDataBinder binder) {
        binder.setDisallowedFields(
                "createdAt", "updatedAt", "statusChangedAt",
                "createdBy", "updatedBy", "statusChangedBy",
                "priceTotal");
    }

    @RequestMapping(value = "/service-orders/{id}")
    public String showForm(@PathVariable Integer id, Model model) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());
        model.addAttribute("serviceDeviceStatusTypes", serviceDeviceStatusTypeRepository.findAll());

        model.addAttribute("serviceOrder", serviceOrder);
        model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
        model.addAttribute("serviceParts", serviceOrder.getServiceParts());

        model.addAttribute("newServiceDevice", new ServiceDevice());
        model.addAttribute("newServicePart", new ServicePart());

        return SERVICE_ORDER_FORM_TEMPLATE;
    }

    @RequestMapping(value = "/service-orders/{id}", method = RequestMethod.POST)
    public String updateServiceOrder(@PathVariable Integer id, Model model, @Valid ServiceOrder updatedServiceOrder, BindingResult bindingResult) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());
            model.addAttribute("serviceDeviceStatusTypes", serviceDeviceStatusTypeRepository.findAll());

            model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
            model.addAttribute("serviceParts", serviceOrder.getServiceParts());

            model.addAttribute("newServiceDevice", new ServiceDevice());
            model.addAttribute("newServicePart", new ServicePart());

            return SERVICE_ORDER_FORM_TEMPLATE;
        } else {
            serviceOrder.setStatusType(updatedServiceOrder.getStatusType());

            List<ServicePart> updatedServiceParts = updatedServiceOrder.getServiceParts();

            for(int i = 0; i < updatedServiceParts.size(); i++) {
                ServicePart updatedServicePart = updatedServiceParts.get(i);
                ServicePart servicePart = serviceOrder.getServiceParts().get(i);

                servicePart.setServiceDevice(updatedServicePart.getServiceDevice());
                servicePart.setPartName(updatedServicePart.getPartName());
                servicePart.setSerialNumber(updatedServicePart.getSerialNumber());
                servicePart.setPartCount(updatedServicePart.getPartCount());
                servicePart.setPartPrice(updatedServicePart.getPartPrice());
            }

            serviceOrderRepository.save(serviceOrder);

            return "redirect:/service-orders/" + id;
        }
    }

    @RequestMapping(value = "/service-orders/{id}", params = {"delete"})
    public String removeServiceDevice(@PathVariable Integer id, @RequestParam("delete") Integer serviceDeviceId) {
        serviceDeviceRepository.delete(serviceDeviceId);

        return "redirect:/service-orders/" + id;
    }

    @RequestMapping(value = "/service-orders/{id}/add-device", method = RequestMethod.POST)
    public String showResultForUpdate(@PathVariable Integer id, Model model, ServiceDevice serviceDevice, BindingResult bindingResult) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        serviceDevice.setServiceOrder(serviceOrder);
        serviceDevice.setStatusType(serviceDeviceStatusTypeRepository.findOne(1));

        serviceDeviceRepository.save(serviceDevice);

        return "redirect:/service-orders/" + id;
    }

    @RequestMapping(value = "/service-orders/{id}/add-part", method = RequestMethod.POST)
    public String addPart(@PathVariable Integer id, Model model,
                          @Valid @ModelAttribute("newServicePart") ServicePart servicePart,
                          BindingResult bindingResult, Principal principal) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("serviceOrder", serviceOrder);
            model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
            model.addAttribute("serviceParts", serviceOrder.getServiceParts());

            model.addAttribute("newServiceDevice", new ServiceDevice());

            return SERVICE_ORDER_FORM_TEMPLATE;
        } else {
            servicePart.setServiceOrder(serviceOrder);
            servicePart.setCreatedAt(new Date());
            servicePart.setCreatedBy(employeeRepository.findByName(principal.getName()));

            servicePartRepository.save(servicePart);

            return "redirect:/service-orders/" + id;
        }
    }
}
