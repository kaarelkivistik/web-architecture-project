package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kaarel on 26.05.16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

    public List<Customer> findByNameLike(String name);

}
