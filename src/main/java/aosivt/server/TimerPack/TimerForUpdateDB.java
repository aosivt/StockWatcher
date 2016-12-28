package aosivt.server.TimerPack;

import java.util.Timer;

/**
 * Created by iskander on 28.12.16.
 */
public class TimerForUpdateDB extends Timer {

    private int periodMinute;
    public TimerForUpdateDB()
    {
        TimerTaskForUpdateDB timerTaskForUpdateDB = new TimerTaskForUpdateDB();
        this.schedule(timerTaskForUpdateDB,1000,5000);
    }

    public TimerForUpdateDB(int _periodMinute)
    {
        this.periodMinute = _periodMinute;
        TimerTaskForUpdateDB timerTaskForUpdateDB = new TimerTaskForUpdateDB();
        this.schedule(timerTaskForUpdateDB,1000,60000 * periodMinute);
    }

    public int getPeriodMinute() {
        return periodMinute;
    }
}
