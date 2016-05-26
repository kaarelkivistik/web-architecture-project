package ee.kaarelkivistik.webarchitecture.repository;

import ee.kaarelkivistik.webarchitecture.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kaarel on 25.05.16.
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    public Employee findByName(String name);

}
