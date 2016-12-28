package aosivt.client.UI.TablePac.ColumnPac;

import aosivt.shared.ReferencesClientServer.BankListRef;
import com.google.gwt.user.cellview.client.TextColumn;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class NameBankColumn extends TextColumn<BankListRef> {
    @Override
    public String getValue(BankListRef bank) {
        return bank.getNameBank();
    }
}
