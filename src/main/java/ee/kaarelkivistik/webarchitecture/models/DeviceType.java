package ee.kaarelkivistik.webarchitecture.models;

import javax.persistence.*;

/**
 * Created by kaarel on 23.05.16.
 */

@Entity
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_type_id_generator")
    @SequenceGenerator(name = "device_type_id_generator", sequenceName = "device_type_id")
    @Column(name = "device_type")
    private Long id;

    @OneToOne
    @JoinColumn(name = "device_type_fk", referencedColumnName = "device_type")
    private DeviceType superType;

    private short level;

    @Column(name = "type_name")
    private String name;

    public DeviceType() {
    }

    public DeviceType(short level, String name) {
        this.level = level;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceType getSuperType() {
        return superType;
    }

    public void setSuperType(DeviceType superType) {
        this.superType = superType;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceType that = (DeviceType) o;

        if (level != that.level) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (superType != null ? !superType.equals(that.superType) : that.superType != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (superType != null ? superType.hashCode() : 0);
        result = 31 * result + (int) level;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "id=" + id +
                ", superType=" + superType +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
