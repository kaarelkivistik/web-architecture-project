package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToOne
    private Employee updatedBy;

    private BigDecimal priceTotal;

    private String note;

    @OneToMany(mappedBy = "serviceOrder")
    @Valid
    List<ServiceDevice> serviceDevices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrder")
    @Valid
    List<ServicePart> serviceParts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceOrder")
    @Valid
    List<ServiceAction> serviceActions = new ArrayList<>();

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

    public List<ServiceDevice> getServiceDevices() {
        return serviceDevices;
    }

    public void setServiceDevices(List<ServiceDevice> serviceDevices) {
        this.serviceDevices = serviceDevices;
    }

    public Employee getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Employee updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<ServicePart> getServiceParts() {
        return serviceParts;
    }

    public void setServiceParts(List<ServicePart> serviceParts) {
        this.serviceParts = serviceParts;
    }

    public List<ServiceAction> getServiceActions() {
        return serviceActions;
    }

    public void setServiceActions(List<ServiceAction> serviceActions) {
        this.serviceActions = serviceActions;
    }
}
