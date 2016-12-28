package aosivt.client;

/**
 * Created by oshchepkovayu on 21.12.16.
 */

import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.shared.ReferencesClientServer.OptionRequest;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("gwtServlet")
public interface GwtAppServiceIntf extends RemoteService {
    List<BankListRef> gwtAppCallServer(OptionRequest data) throws IllegalArgumentException;
}