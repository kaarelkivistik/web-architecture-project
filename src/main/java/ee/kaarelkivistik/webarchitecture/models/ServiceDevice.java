package ee.kaarelkivistik.webarchitecture.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class ServiceDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_device_id_generator")
    @SequenceGenerator(name = "service_device_id_generator", sequenceName = "service_device_id")
    @Column(name = "service_device_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_order_id")
    @JsonBackReference
    private ServiceOrder serviceOrder;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne
    @JoinColumn(name = "service_device_status_type_id")
    private ServiceDeviceStatusType statusType;

    @Column(name = "status_changed")
    private Date statusChangedAt;

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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ServiceDeviceStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(ServiceDeviceStatusType statusType) {
        this.statusType = statusType;
    }

    public Date getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(Date statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    @Override
    public String toString() {
        return "ServiceDevice{" +
                "id=" + id +
                ", serviceOrder=" + serviceOrder +
                ", device=" + device +
                ", statusType=" + statusType +
                ", statusChangedAt=" + statusChangedAt +
                '}';
    }
}
