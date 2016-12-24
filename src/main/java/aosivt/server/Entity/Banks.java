package aosivt.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oshchepkovayu on 20.12.16.
 */
@Entity
@Table(name = "banks")
public class Banks {

    @Id
    @GeneratedValue
    protected Long id;

    protected String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
