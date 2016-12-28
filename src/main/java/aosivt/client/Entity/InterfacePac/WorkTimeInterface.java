package aosivt.client.Entity.InterfacePac;

import aosivt.client.Entity.PivotTable;

import java.util.List;

/**
 * Created by oshchepkovayu on 27.12.16.
 */
public interface WorkTimeInterface {

    public Long getWorktimeId();

    public void setWorktimeId(Long worktimeId);

    public String getWorkTime();

    public void setWorkTime(String workTime);

    public List<PivotTable> getPivotTables();

    public void setPivotTables(List<PivotTable> pivotTables);

}
