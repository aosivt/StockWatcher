package aosivt.client.Entity.InterfacePac;

import aosivt.client.Entity.Address;
import aosivt.client.Entity.Bank;
import aosivt.client.Entity.City;
import aosivt.client.Entity.WorkTime;

/**
 * Created by oshchepkovayu on 27.12.16.
 */
public interface PivotTableInterface {
    public Long getPivotId();

    public void setPivotId(Long pivotId);

    public Bank getBank();

    public void setBank(Bank bank);

    public City getCity();

    public void setCity(City city);

    public Address getAddress();

    public void setAddress(Address address);

    public WorkTime getWorkTime();

    public void setWorkTime(WorkTime workTime);
}
