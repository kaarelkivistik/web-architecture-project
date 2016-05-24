package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceActionStatusType {

    @Id
    private Integer id;

    @Column(name = "type_name")
    private String name;

    public ServiceActionStatusType() {}

    public ServiceActionStatusType(Integer id, String name) {
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
        return "ServiceActionStatusType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
