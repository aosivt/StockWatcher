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
    @Column(name = "city_id",updatable = true)
    protected Long pivotId;

    @ManyToOne(targetEntity = Bank.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "bank_id",referencedColumnName = "bank_id")
    private Bank bank;

    @ManyToOne(targetEntity = Point.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "point_id",referencedColumnName = "point_id")
    private Point point;
}
