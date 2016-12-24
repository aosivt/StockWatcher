package aosivt.server.Entity;

/**
 * Created by oshchepkovayu on 23.12.16.
 */
import javax.persistence.*;

@Entity
@Table(name = "pivotTable")
public class PivotTable {
    @Id
    @GeneratedValue
    @Column(name = "pivot_id",updatable = true)
    protected Long pivotId;

    @ManyToOne(targetEntity = Bank.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(targetEntity = Point.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "point_id")
    private Point point;

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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
