package aosivt.server.TimerPack;

import aosivt.server.GwtAppServiceImpl;

import java.util.TimerTask;

/**
 * Created by iskander on 28.12.16.
 */
public class TimerTaskForUpdateDB extends TimerTask {
    @Override
    public void run() {
        GwtAppServiceImpl.updateDataBase();
    }
}
