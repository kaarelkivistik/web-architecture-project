package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.ServiceRequest;
import ee.kaarelkivistik.webarchitecture.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kaarel on 31.05.16.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class ServiceRequestsRestController {

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @RequestMapping("service-requests")
    public Iterable<ServiceRequest> getAllServiceRequests() {
        Iterable<ServiceRequest> serviceRequests = serviceRequestRepository.findAll();

        return serviceRequests;
    }

}
