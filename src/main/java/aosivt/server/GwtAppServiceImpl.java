package aosivt.server;

import aosivt.client.GwtAppServiceIntf;
import aosivt.server.SoapClient.ConSoapClient;
import aosivt.server.SoapClient.SoapClientConfiguration;
import aosivt.shared.FieldValidator;
import aosivt.shared.ReferencesClientServer.BankListRef;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The server-side implementation of the RPC service.
 */

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppServiceIntf {

    private boolean checkUpdate = false;

    public GwtAppServiceImpl(){Locale.setDefault(Locale.ENGLISH);}


    public List<BankListRef> gwtAppCallServer(String data) throws IllegalArgumentException {


        if (!FieldValidator.isValidData(data)) {
            throw new IllegalArgumentException("Имя должно быть больше трех символов");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        data = escapeHtml(data);
        userAgent = escapeHtml(userAgent);

//      updateDataBase();

        List<BankListRef> listbank = getListFromPivotTable(getListBank("adsfadsf"));

        return listbank;
    }

    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }

    public List<aosivt.server.Entity.PivotTable> getListBank(String data) throws IllegalArgumentException
    {
        if (data.equals("")){return null;}

        aosivt.server.DBPack.WorkingWithDB workingWithDB = new aosivt.server.DBPack.WorkingWithDB();
        List<aosivt.server.Entity.PivotTable> banks =  workingWithDB.getBankList();
        return banks;
    }

    private void updateDataBase()
    {
        if (this.checkUpdate){return;}

        SoapClientConfiguration clientConfiguration = new SoapClientConfiguration();
        ConSoapClient conSoapClient = clientConfiguration.soapClient(clientConfiguration.marshaller());
        aosivt.server.DBPack.WorkingWithDB workingWithDB = new aosivt.server.DBPack.WorkingWithDB(conSoapClient.getListBankResponse());
        workingWithDB.EnterPointFromDB();
        workingWithDB = null;
        this.checkUpdate = true;
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