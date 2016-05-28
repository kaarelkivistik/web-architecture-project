package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.ServiceDevice;
import ee.kaarelkivistik.webarchitecture.models.ServiceOrder;
import ee.kaarelkivistik.webarchitecture.models.ServiceRequest;
import ee.kaarelkivistik.webarchitecture.repository.ServiceDeviceRepository;
import ee.kaarelkivistik.webarchitecture.repository.ServiceDeviceStatusTypeRepository;
import ee.kaarelkivistik.webarchitecture.repository.ServiceOrderRepository;
import ee.kaarelkivistik.webarchitecture.repository.ServiceRequestRepository;
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

/**
 * Created by kaarel on 27.05.16.
 */

@Controller
public class ServiceOrderController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    ServiceDeviceRepository serviceDeviceRepository;

    @Autowired
    ServiceDeviceStatusTypeRepository serviceDeviceStatusTypeRepository;

    @InitBinder
    public void dataBinder(WebDataBinder binder) {
        binder.setDisallowedFields(
                "createdAt", "updatedAt", "statusChangedAt",
                "createdBy", "updatedBy", "statusChangedBy",
                "priceTotal");
    }

    @RequestMapping(value = "/service-orders/{id}", method = RequestMethod.POST)
    public String showResultForUpdate(@PathVariable Integer id, Model model, @Valid ServiceOrder serviceOrder, BindingResult bindingResult) {
        ServiceOrder currentServiceOrder = serviceOrderRepository.findOne(id);

        if(bindingResult.hasErrors())
            return "redirect:/service-requests/" + currentServiceOrder.getServiceRequest().getId();
        else {
            currentServiceOrder.setStatusType(serviceOrder.getStatusType());

            serviceOrderRepository.save(currentServiceOrder);

            return "redirect:/service-requests";
        }
    }

    @RequestMapping(value = "/service-orders/{id}/add-device", method = RequestMethod.POST)
    public String showResultForUpdate(@PathVariable Integer id, Model model, ServiceDevice serviceDevice, BindingResult bindingResult) {
        ServiceOrder currentServiceOrder = serviceOrderRepository.findOne(id);

        serviceDevice.setStatusType(serviceDeviceStatusTypeRepository.findOne(1));

        serviceDeviceRepository.save(serviceDevice);

        return "redirect:/service-requests/" + currentServiceOrder.getServiceRequest().getId();
    }
}
