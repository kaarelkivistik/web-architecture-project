package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 23.05.16.
 */

@Repository
public interface DeviceRepository extends PagingAndSortingRepository<Device, Integer> {

    public Page<Device> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
