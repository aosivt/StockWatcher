package aosivt.client.UI.TablePac.InterfaceService;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public interface GetListBankInterfaceAsync {
    void getListBank(String data, AsyncCallback<Object> callback) throws IllegalArgumentException;
}
