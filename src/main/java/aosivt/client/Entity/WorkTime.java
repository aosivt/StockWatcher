package aosivt.client.Entity;

import aosivt.client.Entity.InterfacePac.WorkTimeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 26.12.16.
 */

public class WorkTime {


    protected Long worktimeId;


    protected String workTime;


    public List<PivotTable> pivotTables = new ArrayList<PivotTable>();

    public Long getWorktimeId() {
        return worktimeId;
    }

    public void setWorktimeId(Long worktimeId) {
        this.worktimeId = worktimeId;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public List<PivotTable> getPivotTables() {
        return pivotTables;
    }

    public void setPivotTables(List<PivotTable> pivotTables) {
        this.pivotTables = pivotTables;
    }

    public int hashCode() {
        return this.getWorkTime().hashCode();
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
        WorkTime e = (WorkTime) obj;
        return (this.getWorkTime().equals(e.getWorkTime()));
    }

    public String toString()
    {
        return getWorkTime();
    }
}
