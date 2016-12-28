package aosivt.client;

import aosivt.server.Entity.PivotTable;
import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.shared.ReferencesClientServer.OptionRequest;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The async counterpart of <code>GwtAppServiceIntf</code>
 */
public interface GwtAppServiceIntfAsync {
    void gwtAppCallServer(OptionRequest data, AsyncCallback<List<BankListRef>> callback) throws IllegalArgumentException;
}