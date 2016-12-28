package aosivt.shared.ReferencesClientServer;

import java.io.Serializable;

/**
 * Created by iskander on 28.12.16.
 */
public class OptionRequest implements Serializable {
    private String nameBank;
    private int enterMinutWorkTimer = 1;

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public int getEnterMinutWorkTimer() {

        return enterMinutWorkTimer;
    }

    public void setEnterMinutWorkTimer(int enterMinutWorkTimer) {
        this.enterMinutWorkTimer = enterMinutWorkTimer;
    }
}
