package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceDeviceStatusType {

    @Id
    @Column(name = "service_device_status_type_id")
    private Integer id;

    @Column(name = "type_name")
    private String name;

    public ServiceDeviceStatusType() {}

    public ServiceDeviceStatusType(Integer id, String name) {
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
        return "ServiceDeviceStatusType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
