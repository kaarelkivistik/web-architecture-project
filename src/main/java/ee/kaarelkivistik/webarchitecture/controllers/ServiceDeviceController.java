package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.ServiceDevice;
import ee.kaarelkivistik.webarchitecture.repository.ServiceDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by kaarel on 28.05.16.
 */

@Controller
public class ServiceDeviceController {

    @Autowired
    ServiceDeviceRepository serviceDeviceRepository;

    @RequestMapping(value = "/service-devices/{id}", method = RequestMethod.POST)
    public String updateServiceDevice(@Valid ServiceDevice serviceDevice, BindingResult bindingResult) {
        serviceDevice.setStatusChangedAt(new Date());

        serviceDeviceRepository.save(serviceDevice);

        return "redirect:/service-requests/" + serviceDevice.getServiceOrder().getServiceRequest().getId();
    }

    @RequestMapping(value = "/service-devices/{id}/delete", method = RequestMethod.POST)
    public String deleteServiceDevice(@PathVariable Integer id) {
        ServiceDevice serviceDevice = serviceDeviceRepository.findOne(id);

        serviceDeviceRepository.delete(id);

        return "redirect:/service-requests/" + serviceDevice.getServiceOrder().getServiceRequest().getId();
    }

}
