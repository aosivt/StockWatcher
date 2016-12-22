package aosivt.server;

import aosivt.client.GwtAppServiceIntf;
import aosivt.server.Entity.Banks;
import aosivt.server.util.HibernateUtil;
import aosivt.shared.FieldValidator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Locale;

/**
 * The server-side implementation of the RPC service.
 */

public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppServiceIntf {

    public String gwtAppCallServer(String data) throws IllegalArgumentException {

        Locale.setDefault(Locale.ENGLISH);

        if (!FieldValidator.isValidData(data)) {
            throw new IllegalArgumentException("Имя должно быть больше трех символов");
        }
        WorkingWithDB workingWithDB = new WorkingWithDB(getListXml());
        workingWithDB.UpsertDBOra();

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

    protected static List<Banks> getListXml()
    {

        System.out.println("Select List StructureXml");


        List<Banks> resultlist = null;
        Session session = null;

        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query q = session.createQuery("From Banks");
            resultlist = q.list();
            session.clear();
            session.close();
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  resultlist;
    }

}