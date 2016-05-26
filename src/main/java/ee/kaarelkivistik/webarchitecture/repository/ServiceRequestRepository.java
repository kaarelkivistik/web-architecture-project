package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.ServiceRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 25.05.16.
 */

@Repository
public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Integer> {
}
