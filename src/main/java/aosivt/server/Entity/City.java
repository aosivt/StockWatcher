package aosivt.server.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    protected Long cityId;

    @Column(name = "namecity")
    protected String nameCity;

    @OneToMany(targetEntity = PivotTable.class,fetch = FetchType.EAGER,mappedBy="city", cascade=CascadeType.ALL)
    public List<PivotTable> pivotTables = new ArrayList<PivotTable>();

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public List<PivotTable> getPivotTables() {
        return pivotTables;
    }

    public void setPivotTables(List<PivotTable> pivotTables) {
        this.pivotTables = pivotTables;
    }

    public int hashCode() {
        return this.getNameCity().hashCode();
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
        City e = (City) obj;
        return (this.getNameCity().equals(e.getNameCity()));
    }

    public String toString()
    {
        return getNameCity();
    }
}
