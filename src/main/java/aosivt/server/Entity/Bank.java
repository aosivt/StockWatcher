package aosivt.server.Entity;

/**
 * Created by oshchepkovayu on 22.12.16.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue
    protected Long bank_id;

    protected String name;

    public Long getBank_id() {
        return bank_id;
    }

    public void setBank_id(Long bank_id) {
        this.bank_id = bank_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
