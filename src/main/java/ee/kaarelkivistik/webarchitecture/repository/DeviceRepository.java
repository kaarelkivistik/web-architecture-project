package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 23.05.16.
 */

public interface DeviceRepository extends CrudRepository<Device, Long> {
}
