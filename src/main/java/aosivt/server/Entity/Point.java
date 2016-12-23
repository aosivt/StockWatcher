package aosivt.server.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
@Entity
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "point_id",updatable = true)
    protected Long pointId;

//    @Column(name = "bank_id",nullable = false,insertable = false,updatable = false)
//    protected Long bankId;

//    @ManyToOne(targetEntity = Bank.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
//    @JoinColumn(name = "bank_id",referencedColumnName = "bank_id")
//    private Bank bank;

    @OneToMany(targetEntity = PivotTable.class,fetch = FetchType.EAGER,mappedBy="pointId", cascade=CascadeType.ALL)
    public List<PivotTable> pivotTables = new ArrayList<PivotTable>();


    @Column(name = "city")
    protected String city;
    @Column(name = "address")
    protected String address;
    @Column(name = "time")
    protected String time;


    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
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
