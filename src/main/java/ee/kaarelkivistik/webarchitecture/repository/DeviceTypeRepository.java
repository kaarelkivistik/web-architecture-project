package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 24.05.16.
 */

@Repository
public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer>{
}
