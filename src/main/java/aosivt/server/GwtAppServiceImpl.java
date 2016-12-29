package aosivt.server;

import aosivt.client.GwtAppServiceIntf;
import aosivt.server.SoapClient.ConSoapClient;
import aosivt.server.SoapClient.SoapClientConfiguration;
import aosivt.server.TimerPack.TimerForUpdateDB;
import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.shared.ReferencesClientServer.OptionRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The server-side implementation of the RPC service.
 */

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppServiceIntf {



    private TimerForUpdateDB updateTimer;


    public GwtAppServiceImpl(){Locale.setDefault(Locale.ENGLISH);}


    public List<BankListRef> gwtAppCallServer(OptionRequest data) throws IllegalArgumentException {


//        if (this.updateTimer==null) {
//            updateDataBase();
//            updateTimer = new TimerForUpdateDB(data.getEnterMinutWorkTimer());
//        }
//        else if (this.updateTimer.getPeriodMinute()!=data.getEnterMinutWorkTimer())
//        {
//            this.updateTimer.cancel();
//            this.updateTimer = null;
//            this.updateTimer = new TimerForUpdateDB(data.getEnterMinutWorkTimer());
//        }

        List<BankListRef> listbank = getListFromPivotTable(getListBank(data.getNameBank()));

        return listbank;
    }

    private List<aosivt.server.Entity.PivotTable> getListBank(String data) throws IllegalArgumentException
    {
        if (data.equals("")){return null;}

        aosivt.server.DBPack.WorkingWithDB workingWithDB = new aosivt.server.DBPack.WorkingWithDB();
        workingWithDB.selectBankList(data);
        List<aosivt.server.Entity.PivotTable> banks =  workingWithDB.getBankList();
        return banks;
    }

    public static void updateDataBase()
    {
        SoapClientConfiguration clientConfiguration = new SoapClientConfiguration();
        ConSoapClient conSoapClient = clientConfiguration.soapClient(clientConfiguration.marshaller());
        aosivt.server.DBPack.WorkingWithDB workingWithDB =
                new aosivt.server.DBPack.WorkingWithDB(conSoapClient.getListBankResponse());
        workingWithDB.EnterPointFromDB();
        workingWithDB = null;
    }

    private List<BankListRef> getListFromPivotTable(List<aosivt.server.Entity.PivotTable> _list)
    {
        List<BankListRef> listBankRef = new ArrayList<BankListRef>();
        BankListRef bankRef = null;
        for (aosivt.server.Entity.PivotTable pivotTable: _list)
        {
            bankRef = new BankListRef();
            bankRef.setAddress(pivotTable.getAddress().toString());
            bankRef.setCity(pivotTable.getCity().toString());
            bankRef.setNameBank(pivotTable.getBank().toString());
            bankRef.setTime(pivotTable.getWorkTime().toString());
            listBankRef.add(bankRef);
        }

        return listBankRef;
    }




}