package aosivt.server;

import aosivt.client.GwtAppServiceIntf;
import aosivt.shared.FieldValidator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppServiceIntf {


    public String gwtAppCallServer(String data) throws IllegalArgumentException {
        if (!FieldValidator.isValidData(data)) {
            throw new IllegalArgumentException("Имя должно быть больше трех символов");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        data = escapeHtml(data);
        userAgent = escapeHtml(userAgent);

        return "Привет, " + data + "!<br> Инфо сервера: " + serverInfo + ".<br> Вы используете:" +
                "<br>" + userAgent;
    }

    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}