package aosivt.client.UI.TablePac.InterfaceService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
@RemoteServiceRelativePath("gwtServlet")
public interface GetListBankInterface extends RemoteService {
    Object getListBank(String data) throws IllegalArgumentException;
}
