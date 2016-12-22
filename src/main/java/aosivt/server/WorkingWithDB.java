package aosivt.server;

import aosivt.server.Entity.Bank;
import aosivt.server.Entity.Banks;
import aosivt.server.Entity.Point;
import aosivt.server.XmlGetter.StructureXml;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
public class WorkingWithDB {
    private List<Banks> xmlStrgin = new ArrayList<Banks>();
//    private List<String> xmlStrgin = new ArrayList<String>();
    private List<StructureXml> structureXmlList = new ArrayList<StructureXml>();


    public void  _intit()
    {

    }
    public WorkingWithDB()
    {

    }
    public WorkingWithDB(List<Banks> _xmlStrgin)
    {
        this.xmlStrgin = _xmlStrgin;
    }


    public boolean UpsertDBOra()
    {
        if (convertXml())
        {
        Bank bank = null;
        Point point = null;
        List<Point> pointList = new ArrayList<Point>();
        Map<Bank,List<Point>> bankPoints = new HashMap<Bank, List<Point>>();
        for (StructureXml structureXml : this.structureXmlList)
        {
            bank = new Bank();
            bank.setName(structureXml.getNameBank());

            point = new Point();
            point.setAddress(structureXml.getAddress());
            point.setTime(structureXml.getTime());
            point.setCity(structureXml.getCity());
            pointList.add(point);
            bankPoints.put(bank,pointList);
        }
        }
        return true;
    }

    public boolean convertXml()
    {
//        String xml_str = this.createXmlString();
//        this.xmlStrgin.add(xml_str);
//        this.xmlStrgin.add(xml_str);

        for (Banks string_content : this.xmlStrgin)
        {
            try {

                InputStream stream = new ByteArrayInputStream(string_content.getContent().getBytes());
                JAXBContext jaxbContext = JAXBContext.newInstance(StructureXml.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                StructureXml response = (StructureXml) jaxbUnmarshaller.unmarshal(stream);
                structureXmlList.add(response);

            }catch (Exception e)
            {
                        System.out.print("Exception message:" + e.getLocalizedMessage());
//                return false;
            }

        }

        return true;
    }

    private static void fieldingDB(String _input_string)
    {
        Session session = null;

        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("Inserting Record");
            Banks banks = new Banks();
            banks.setContent(_input_string);
            session.save(banks);
            transaction.commit();
            session.clear();
            session.close();
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private String createXmlString()
    {
        String error_exception = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StructureXml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StructureXml xml =new StructureXml();

            xml.setNameBank("NameBank");
            xml.setCity("Kemerovo");
            xml.setAddress("street");
            xml.setTime("16:00 до 17:00");

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(xml, sw);
            String xmlString = sw.toString();
            return xmlString;
        }catch (Exception e)
        {
            error_exception = e.getLocalizedMessage();
            System.out.print("Exception message:" + e.getLocalizedMessage());
        }
        return error_exception;
    }
}
