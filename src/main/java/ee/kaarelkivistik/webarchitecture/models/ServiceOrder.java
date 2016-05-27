package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_order_id_generator")
    @SequenceGenerator(name = "service_order_id_generator", sequenceName = "service_order_id")
    @Column(name = "service_order_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "so_status_type_id")
    @NotNull
    private ServiceOrderStatusType statusType;

    @OneToOne
    private Employee createdBy;

    @OneToOne
    @JoinColumn(name = "service_request_id")
    private ServiceRequest serviceRequest;

    @Column(name = "created")
    private Date createdAt;

    @Column(name = "updated")
    private Date updatedAt;

    @Column(name = "status_changed")
    private Date statusChangedAt;

    @OneToOne
    private Employee statusChangedBy;

    @NotNull
    private BigDecimal priceTotal;

    private String note;

    public ServiceOrder() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceOrderStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(ServiceOrderStatusType statusType) {
        this.statusType = statusType;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(Date statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    public Employee getStatusChangedBy() {
        return statusChangedBy;
    }

    public void setStatusChangedBy(Employee statusChangedBy) {
        this.statusChangedBy = statusChangedBy;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
