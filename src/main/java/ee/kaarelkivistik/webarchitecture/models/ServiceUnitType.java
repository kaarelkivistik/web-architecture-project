package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kaarel on 30.05.16.
 */

@Entity
public class ServiceUnitType {

    @Id
    @Column(name = "service_unit_type_id")
    private Integer id;

    @Column(name = "type_name")
    private String name;

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
}
