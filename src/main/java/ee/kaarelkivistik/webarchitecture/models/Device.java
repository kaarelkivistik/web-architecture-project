package ee.kaarelkivistik.webarchitecture.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_generator")
    @SequenceGenerator(name = "device_id_generator", sequenceName = "device_id")
    @Column(name = "device_id")
    private Integer id;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    private String name;

    @OneToOne
    @JoinColumn(name = "device_type_id")
    @NotNull
    private DeviceType type;

    @Size(max = 100)
    @Column(name = "reg_no")
    @NotNull
    @NotEmpty
    private String regNumber;

    private String description;

    @Size(max = 100)
    private String model;

    @Size(max = 100)
    @NotNull
    @NotEmpty
    private String manufacturer;

    public Device() {
    }

    public Device(String name, DeviceType type, String regNumber, String description, String model, String manufacturer) {
        this.name = name;
        this.type = type;
        this.regNumber = regNumber;
        this.description = description;
        this.model = model;
        this.manufacturer = manufacturer;
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

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", regNumber='" + regNumber + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}

