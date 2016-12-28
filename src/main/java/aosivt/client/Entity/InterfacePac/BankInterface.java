package aosivt.client.Entity.InterfacePac;

import aosivt.client.Entity.PivotTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 27.12.16.
 */
public interface BankInterface {

    public Long getBankId();

    public void setBankId(Long bankId);

    public String getName();

    public void setName(String name);

    public List<PivotTable> getPivotTables();

    public void setPivotTables(List<PivotTable> pivotTables);
}
