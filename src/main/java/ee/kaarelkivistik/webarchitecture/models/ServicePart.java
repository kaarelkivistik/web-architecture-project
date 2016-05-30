package ee.kaarelkivistik.webarchitecture.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServicePart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_part_id_generator")
    @SequenceGenerator(name = "service_part_id_generator", sequenceName = "service_part_id")
    @Column(name = "service_part_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_order_id")
    private ServiceOrder serviceOrder;

    @OneToOne
    @JoinColumn(name = "service_device_id")
    private ServiceDevice serviceDevice;

    @NotNull
    @NotEmpty
    private String partName;

    @Column(name = "serial_no")
    private String serialNumber;

    @NotNull
    @Min(0)
    private Double partCount = Double.valueOf(1);

    @NotNull
    private BigDecimal partPrice = BigDecimal.ZERO;

    @Column(name = "created")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public ServiceDevice getServiceDevice() {
        return serviceDevice;
    }

    public void setServiceDevice(ServiceDevice serviceDevice) {
        this.serviceDevice = serviceDevice;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getPartCount() {
        return partCount;
    }

    public void setPartCount(Double partCount) {
        this.partCount = partCount;
    }

    public BigDecimal getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(BigDecimal partPrice) {
        this.partPrice = partPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }
}
