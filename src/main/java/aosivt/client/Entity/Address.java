package aosivt.client.Entity;

import aosivt.client.Entity.InterfacePac.AddressInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class Address {

    protected Long addressId;


    protected String pointAddress;


    public List<PivotTable> pivotTables = new ArrayList<PivotTable>();

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public List<PivotTable> getPivotTables() {
        return pivotTables;
    }

    public void setPivotTables(List<PivotTable> pivotTables) {
        this.pivotTables = pivotTables;
    }

    public int hashCode() {
        return this.getPointAddress().hashCode();
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
        Address e = (Address) obj;
        return (this.getPointAddress().equals(e.getPointAddress()));
    }

    public String toString()
    {
        return getPointAddress();
    }
}
