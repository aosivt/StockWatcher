package aosivt.client.Entity;

/**
 * Created by oshchepkovayu on 23.12.16.
 */

import aosivt.client.Entity.InterfacePac.PivotTableInterface;

public class PivotTable {

    protected Long pivotId;


    private Bank bank;

    private City city;


    private Address address;


    private WorkTime workTime;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }
}
