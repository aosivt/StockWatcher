package aosivt.server;

import aosivt.client.UI.TablePac.InterfaceService.GetListBankInterface;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class AppServiceGetListBank extends RemoteServiceServlet implements GetListBankInterface {

    @Override
    public Object getListBank(String data) throws IllegalArgumentException {
        return null;
    }

    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}
