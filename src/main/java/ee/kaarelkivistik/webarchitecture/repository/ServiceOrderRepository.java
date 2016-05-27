package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.ServiceOrder;
import ee.kaarelkivistik.webarchitecture.models.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kaarel on 27.05.16.
 */

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Integer>{

    public ServiceOrder findByServiceRequest(ServiceRequest serviceRequest);

}
