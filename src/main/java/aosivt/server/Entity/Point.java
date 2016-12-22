package aosivt.server.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
@Entity
@Table(name = "point")
public class Point {

    protected Long id_city;
    protected String city;
    protected String address;
    protected String time;

    public Long getId_city() {
        return id_city;
    }

    public void setId_city(Long id_city) {
        this.id_city = id_city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
