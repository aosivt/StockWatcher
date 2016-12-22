package aosivt.server.Entity;

/**
 * Created by oshchepkovayu on 22.12.16.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue
    @Column(name = "bank_id")
    protected Long bankId;

    @Column(name = "nameBank")
    protected String name;

    @OneToMany(mappedBy="bankId", cascade=CascadeType.PERSIST)
    public List<Point> point = new ArrayList<Point>();


    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoint() {
        return point;
    }

    public void setPoint(List<Point> point) {
        this.point = point;
    }
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Bank e = (Bank) obj;
        return (this.getName().equals(e.getName()));
    }
}
