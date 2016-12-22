package aosivt.server.XmlGetter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by oshchepkovayu on 20.12.16.
 */
@XmlRootElement(name="banks")
@XmlAccessorType(XmlAccessType.FIELD)
public class StructureXml {

    public StructureXml(){}

    @XmlElement(name="nameBank")
    protected String nameBank;
    @XmlElement(name="city")
    protected String city;
    @XmlElement(name="address")
    protected String address;
    @XmlElement(name="time")
    protected String time;

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
