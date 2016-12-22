package aosivt.server.Entity;

import javax.persistence.*;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
@Entity
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    protected Long cityId;

    @Column(name = "bank_id")
    protected Long bankId;

    @ManyToOne
    private Bank bank;

    @Column(name = "city")
    protected String city;
    @Column(name = "address")
    protected String address;
    @Column(name = "time")
    protected String time;


    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
