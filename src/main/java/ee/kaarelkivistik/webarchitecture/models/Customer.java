package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kaarel on 24.05.16.
 */
@Entity
public class Customer {

    @Id
    private Integer id;

    private String name;

    public Customer() {}

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}