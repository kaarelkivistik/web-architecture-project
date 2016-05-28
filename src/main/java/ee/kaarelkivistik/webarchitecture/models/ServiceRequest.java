package ee.kaarelkivistik.webarchitecture.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_request_id_generator")
    @SequenceGenerator(name = "service_request_id_generator", sequenceName = "service_request_id")
    @Column(name = "service_request_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_request_status_type_id")
    private ServiceRequestStatusType statusType;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @Column(name = "created")
    private Date createdAt;

    @Column(name = "service_desc_by_customer")
    @NotEmpty
    private String descriptionByCustomer;

    @Column(name = "service_desc_by_employee")
    @NotEmpty
    private String descriptionByEmployee;

    @OneToOne(mappedBy = "serviceRequest")
    private ServiceOrder serviceOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceRequestStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(ServiceRequestStatusType statusType) {
        this.statusType = statusType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescriptionByCustomer() {
        return descriptionByCustomer;
    }

    public void setDescriptionByCustomer(String descriptionByCustomer) {
        this.descriptionByCustomer = descriptionByCustomer;
    }

    public String getDescriptionByEmployee() {
        return descriptionByEmployee;
    }

    public void setDescriptionByEmployee(String descriptionByEmployee) {
        this.descriptionByEmployee = descriptionByEmployee;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "id=" + id +
                ", statusType=" + statusType +
                ", customer=" + customer +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", descriptionByCustomer='" + descriptionByCustomer + '\'' +
                ", descriptionByEmployee='" + descriptionByEmployee + '\'' +
                '}';
    }
}
