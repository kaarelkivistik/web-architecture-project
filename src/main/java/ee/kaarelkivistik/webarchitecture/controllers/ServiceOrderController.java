package ee.kaarelkivistik.webarchitecture.controllers;

import ee.kaarelkivistik.webarchitecture.models.ServiceOrder;
import ee.kaarelkivistik.webarchitecture.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kaarel on 27.05.16.
 */

@Controller
public class ServiceOrderController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

}