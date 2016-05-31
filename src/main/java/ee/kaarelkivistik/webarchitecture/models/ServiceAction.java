package ee.kaarelkivistik.webarchitecture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceAction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_action_id_generator")
    @SequenceGenerator(name = "service_action_id_generator", sequenceName = "service_action_id")
    @Column(name = "service_action_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_action_status_type_id")
    private ServiceActionStatusType serviceActionStatusType;

    @OneToOne
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @OneToOne
    @JoinColumn(name = "service_device_id")
    private ServiceDevice serviceDevice;

    @OneToOne
    @JoinColumn(name = "service_order_id")
    @JsonBackReference
    private ServiceOrder serviceOrder;

    @NotNull
    private Double serviceAmount = Double.valueOf(1);

    private BigDecimal price;

    private String actionDescription;

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

    public ServiceActionStatusType getServiceActionStatusType() {
        return serviceActionStatusType;
    }

    public void setServiceActionStatusType(ServiceActionStatusType serviceActionStatusType) {
        this.serviceActionStatusType = serviceActionStatusType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceDevice getServiceDevice() {
        return serviceDevice;
    }

    public void setServiceDevice(ServiceDevice serviceDevice) {
        this.serviceDevice = serviceDevice;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
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
