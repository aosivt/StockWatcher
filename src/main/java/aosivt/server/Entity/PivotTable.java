package aosivt.server.Entity;

/**
 * Created by oshchepkovayu on 23.12.16.
 */
import javax.persistence.*;

@Entity
@Table(name = "pivotTable")
public class PivotTable  {
    @Id
    @GeneratedValue
    @Column(name = "pivot_id",updatable = true)
    protected Long pivotId;

    @ManyToOne(targetEntity = Bank.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(targetEntity = City.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(targetEntity = Address.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(targetEntity = WorkTime.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "worktime_id")
    private WorkTime workTime;

    public Long getPivotId() {
        return pivotId;
    }

    public void setPivotId(Long pivotId) {
        this.pivotId = pivotId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }
}
