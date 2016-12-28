package aosivt.shared.ReferencesClientServer;

import java.io.Serializable;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class BankListRef implements Serializable{
    private String nameBank;
    private String city;
    private String address;
    private String time;


    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
