package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.*;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_id_generator")
    @SequenceGenerator(name = "device_id_generator", sequenceName = "device_id")
    @Column(name = "device")
    private long id;

    private String name;

    public Device() {
    }

    public Device(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
