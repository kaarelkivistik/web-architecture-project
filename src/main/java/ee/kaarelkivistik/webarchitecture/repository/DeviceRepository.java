package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.Device;
import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 23.05.16.
 */

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {
}
