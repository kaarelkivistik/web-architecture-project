package ee.kaarelkivistik.webarchitecture.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
@Table(name = "device_type")
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_type_id_generator")
    @SequenceGenerator(name = "device_type_id_generator", sequenceName = "device_type_id")
    @Column(name = "device_type_id")
    private Integer id;

    private Integer superTypeId;

    private short level;

    @Column(name = "type_name")
    private String name;

    public DeviceType() {
    }

    public DeviceType(short level, String name) {
        this.level = level;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuperTypeId() {
        return superTypeId;
    }

    public void setSuperTypeId(Integer superTypeId) {
        this.superTypeId = superTypeId;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "id=" + id +
                ", superType=" + superTypeId +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
