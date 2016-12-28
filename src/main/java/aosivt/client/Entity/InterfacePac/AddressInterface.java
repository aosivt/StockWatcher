package aosivt.client.Entity.InterfacePac;

import aosivt.client.Entity.PivotTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 27.12.16.
 */
public interface AddressInterface {

    public Long getAddressId();

    public void setAddressId(Long addressId);

    public String getPointAddress();

    public void setPointAddress(String pointAddress);

    public List<PivotTable> getPivotTables();

    public void setPivotTables(List<PivotTable> pivotTables) ;
}
