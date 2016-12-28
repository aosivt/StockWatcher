package aosivt.client.UI.TablePac;

import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.client.UI.TablePac.ColumnPac.AddressBankColumn;
import aosivt.client.UI.TablePac.ColumnPac.CityBankColumn;
import aosivt.client.UI.TablePac.ColumnPac.NameBankColumn;
import aosivt.client.UI.TablePac.ColumnPac.WorkTimeBankColumn;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class TableDataBankList extends CellTable<BankListRef> {

    private List<BankListRef> bankList  = new   ArrayList<BankListRef>();
    private TextColumn        nameBank  = new           NameBankColumn();
    private TextColumn        city      = new           CityBankColumn();
    private TextColumn        address   = new        AddressBankColumn();
    private TextColumn        time      = new       WorkTimeBankColumn();

    public TableDataBankList ()
    {
        BankListRef bankListRef = new BankListRef();
        bankListRef.setTime("sfdgfdg");
        bankListRef.setAddress("sfdgfdg");
        bankListRef.setCity("sfdgfdg");
        bankListRef.setNameBank("sfdgfdg");

        List<BankListRef> bankListRefsL = new ArrayList<BankListRef>();
        bankListRefsL.add(bankListRef);

        bankList = bankListRefsL;

    this.addColumn(this.nameBank);
    this.addColumn(this.city);
    this.addColumn(this.address);
    this.addColumn(this.time);

        ListDataProvider<BankListRef> dataProviderBank = new ListDataProvider<BankListRef>(this.bankList);
        dataProviderBank.addDataDisplay(this);
    }

    public TableDataBankList (List<BankListRef> _bankList)
    {
        this.addColumn(this.nameBank);
        this.addColumn(this.city);
        this.addColumn(this.address);
        this.addColumn(this.time);
        this.bankList = _bankList;

        ListDataProvider<BankListRef> dataProviderBank
                = new ListDataProvider<BankListRef>(this.bankList);
        dataProviderBank.addDataDisplay(this);
    }

}
