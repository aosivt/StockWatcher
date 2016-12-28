package aosivt.client.Entity.InterfacePac;

import aosivt.client.Entity.PivotTable;

import java.util.List;

/**
 * Created by oshchepkovayu on 27.12.16.
 */
public interface CityInterface {

    public Long getCityId();

    public void setCityId(Long cityId);

    public String getNameCity();

    public void setNameCity(String nameCity);

    public List<PivotTable> getPivotTables();

    public void setPivotTables(List<PivotTable> pivotTables);
}
