package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kaarel on 26.05.16.
 */

public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer> {
}
