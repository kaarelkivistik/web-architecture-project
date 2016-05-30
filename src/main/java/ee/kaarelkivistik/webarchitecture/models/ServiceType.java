package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by kaarel on 23.05.16.
 */
@Entity
public class ServiceType {

    @Id
    @Column(name = "service_type_id")
    private Integer id;

    @Column(name = "type_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "service_unit_type_id")
    private ServiceUnitType serviceUnitType;

    private BigDecimal servicePrice;

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

    public ServiceUnitType getServiceUnitType() {
        return serviceUnitType;
    }

    public void setServiceUnitType(ServiceUnitType serviceUnitType) {
        this.serviceUnitType = serviceUnitType;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }
}
