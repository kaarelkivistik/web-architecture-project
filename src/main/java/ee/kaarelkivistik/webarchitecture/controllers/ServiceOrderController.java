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
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    ServiceActionRepository serviceActionRepository;

    @Autowired
    ServiceActionStatusTypeRepository serviceActionStatusTypeRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Autowired
    ServiceUnitTypeRepository serviceUnitTypeRepository;

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

        populateTypes(model);

        model.addAttribute("serviceOrder", serviceOrder);
        model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
        model.addAttribute("serviceParts", serviceOrder.getServiceParts());
        model.addAttribute("serviceActions", serviceOrder.getServiceActions());

        model.addAttribute("newServiceDevice", new ServiceDevice());
        model.addAttribute("newServicePart", new ServicePart());
        model.addAttribute("newServiceAction", new ServiceAction());

        return SERVICE_ORDER_FORM_TEMPLATE;
    }

    private void populateTypes(Model model) {
        model.addAttribute("orderStatusTypes", serviceOrderStatusTypeRepository.findAll());
        model.addAttribute("serviceDeviceStatusTypes", serviceDeviceStatusTypeRepository.findAll());
        model.addAttribute("serviceTypes", serviceTypeRepository.findAll());
        model.addAttribute("serviceUnitTypes", serviceUnitTypeRepository.findAll());
    }

    @RequestMapping(value = "/service-orders/{id}", method = RequestMethod.POST)
    public String updateServiceOrder(@PathVariable Integer id, Model model, @Valid ServiceOrder updatedServiceOrder, BindingResult bindingResult) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        if(bindingResult.hasErrors()) {
            populateTypes(model);

            model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
            model.addAttribute("serviceParts", serviceOrder.getServiceParts());
            model.addAttribute("serviceActions", serviceOrder.getServiceActions());

            model.addAttribute("newServiceDevice", new ServiceDevice());
            model.addAttribute("newServicePart", new ServicePart());
            model.addAttribute("newServiceAction", new ServiceAction());

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

            List<ServiceDevice> updatedServiceDevices = updatedServiceOrder.getServiceDevices();

            for(int i = 0; i < updatedServiceDevices.size(); i++) {
                ServiceDevice updatedServiceDevice = updatedServiceDevices.get(i);
                ServiceDevice serviceDevice = serviceOrder.getServiceDevices().get(i);

                if(!Objects.equals(serviceDevice.getStatusType().getId(), updatedServiceDevice.getId())) {
                    serviceDevice.setStatusType(updatedServiceDevice.getStatusType());
                    serviceDevice.setStatusChangedAt(new Date());
                }
            }

            List<ServiceAction> updatedServiceActions = updatedServiceOrder.getServiceActions();

            for(int i = 0; i < updatedServiceActions.size(); i++) {
                ServiceAction updatedServiceAction = updatedServiceActions.get(i);
                ServiceAction serviceAction = serviceOrder.getServiceActions().get(i);

                serviceAction.setPrice(updatedServiceAction.getPrice());
                serviceAction.setActionDescription(updatedServiceAction.getActionDescription());
                serviceAction.setServiceType(updatedServiceAction.getServiceType());
                serviceAction.setServiceAmount(updatedServiceAction.getServiceAmount());
                serviceAction.setServiceDevice(updatedServiceAction.getServiceDevice());
            }

            serviceOrderRepository.save(serviceOrder);

            return "redirect:/service-orders/" + id;
        }
    }

    @RequestMapping(value = "/service-orders/{id}", params = {"deleteDevice"})
    public String deleteServiceDevice(@PathVariable Integer id, @RequestParam("deleteDevice") Integer serviceDeviceId) {
        serviceDeviceRepository.delete(serviceDeviceId);

        return "redirect:/service-orders/" + id;
    }

    @RequestMapping(value = "/service-orders/{id}", params = {"deleteAction"})
    public String deleteServiceAction(@PathVariable Integer id, @RequestParam("deleteAction") Integer serviceActionId) {
        serviceActionRepository.delete(serviceActionId);

        return "redirect:/service-orders/" + id;
    }

    @RequestMapping(value = "/service-orders/{id}", params = {"deletePart"})
    public String deleteServicePart(@PathVariable Integer id, @RequestParam("deletePart") Integer servicePartId) {
        servicePartRepository.delete(servicePartId);

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
            populateTypes(model);

            model.addAttribute("serviceOrder", serviceOrder);
            model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
            model.addAttribute("serviceParts", serviceOrder.getServiceParts());
            model.addAttribute("serviceActions", serviceOrder.getServiceActions());

            model.addAttribute("newServiceDevice", new ServiceDevice());
            model.addAttribute("newServiceAction", new ServiceAction());

            return SERVICE_ORDER_FORM_TEMPLATE;
        } else {
            servicePart.setServiceOrder(serviceOrder);
            servicePart.setCreatedAt(new Date());
            servicePart.setCreatedBy(employeeRepository.findByName(principal.getName()));

            servicePartRepository.save(servicePart);

            return "redirect:/service-orders/" + id;
        }
    }

    @RequestMapping(value = "/service-orders/{id}/add-action", method = RequestMethod.POST)
    public String addAction(@PathVariable Integer id, Model model,
                          @Valid @ModelAttribute("newServiceAction") ServiceAction serviceAction,
                          BindingResult bindingResult, Principal principal) {
        ServiceOrder serviceOrder = serviceOrderRepository.findOne(id);

        if(bindingResult.hasErrors()) {
            populateTypes(model);

            model.addAttribute("serviceOrder", serviceOrder);
            model.addAttribute("serviceDevices", serviceOrder.getServiceDevices());
            model.addAttribute("serviceParts", serviceOrder.getServiceParts());
            model.addAttribute("serviceActions", serviceOrder.getServiceActions());

            model.addAttribute("newServiceDevice", new ServiceDevice());
            model.addAttribute("newServicePart", new ServicePart());

            return SERVICE_ORDER_FORM_TEMPLATE;
        } else {
            serviceAction.setServiceOrder(serviceOrder);
            serviceAction.setCreatedAt(new Date());
            serviceAction.setCreatedBy(employeeRepository.findByName(principal.getName()));
            serviceAction.setServiceActionStatusType(serviceActionStatusTypeRepository.findOne(1));

            serviceAction.setPrice(serviceAction.getServiceType().getServicePrice());

            serviceActionRepository.save(serviceAction);

            return "redirect:/service-orders/" + id;
        }
    }

}
