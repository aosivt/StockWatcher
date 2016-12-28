package aosivt.client.Entity;

import aosivt.client.Entity.InterfacePac.CityInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 26.12.16.
 */

public class City {

    protected Long cityId;


    protected String nameCity;


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
