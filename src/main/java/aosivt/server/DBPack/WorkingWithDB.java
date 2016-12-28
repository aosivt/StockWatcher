package aosivt.server.DBPack;

import aosivt.server.DBPack.UpsertAddress.UpsertAddressObj;
import aosivt.server.DBPack.UpsertBank.UpsertBankObj;
import aosivt.server.DBPack.UpsertCity.UpsertCityObj;
import aosivt.server.DBPack.UpsertPivotTable.UpsertPivotTableObj;
import aosivt.server.DBPack.UpsertWorkTime.UpsertWorkTimeObj;
import aosivt.server.Entity.*;
import aosivt.server.XmlGetter.StructureXml;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import structureXML.wsdl.GetListBankResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
public class WorkingWithDB {
    private List<Banks> xmlStrgin;
    private List<structureXML.wsdl.Bank> xmlStrginInput;
    private String hsql_query;
    //    private List<String> xmlStrgin = new ArrayList<String>();
    private List<StructureXml> structureXmlList ;

    private List<PivotTable> bankList ;

    public WorkingWithDB()
    {
        this.hsql_query = "select b.name,p.city,p.address,p.time " +
                "from Bank b inner join Point p on b.bankId=p.bankId";

        this.bankList = new ArrayList<PivotTable>();
        selectBankList();
    }

    public WorkingWithDB(GetListBankResponse _getGetListBankResponses)
    {
        this.structureXmlList = new ArrayList<StructureXml>();
        this.xmlStrginInput = _getGetListBankResponses.getBank();
    }
    //
//
    public boolean EnterPointFromDB()
    {
        if (convertXml())
        {
            Bank bank = new Bank();
            City city = new City();
            Address address = new Address();
            WorkTime workTime = new WorkTime();
            PivotTable pivotTable = new PivotTable();

            for (StructureXml structureXml : this.structureXmlList)
            {
                bank = new UpsertBankObj(structureXml.getNameBank()).getBank();

                city = new UpsertCityObj(structureXml.getCity()).getCity();

                address = new UpsertAddressObj(structureXml.getAddress()).getAddress();

                workTime = new UpsertWorkTimeObj(structureXml.getTime()).getWorkTime();

                pivotTable = new UpsertPivotTableObj(bank,city,address,workTime).getPivotTable();

            }

             bank = null;
             city = null;
             address = null;
             workTime = null;
             pivotTable = null;

        }

        return true;
    }

    private boolean convertXml()
    {

        if (this.xmlStrginInput.size()<0) return false;

        for (structureXML.wsdl.Bank string_content : this.xmlStrginInput)
        {
            try {

                InputStream stream = new ByteArrayInputStream(string_content.getXmlString().getBytes());
                JAXBContext jaxbContext = JAXBContext.newInstance(StructureXml.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                structureXmlList.add((StructureXml) jaxbUnmarshaller.unmarshal(stream));

            }catch (Exception e)
            {
                System.out.print("Exception message:" + e.getLocalizedMessage());

            }

        }

        return true;
    }

    private void selectBankList()
    {
        Session session = null;

        this.hsql_query =
                "select pt " +
                        "from PivotTable pt " +
                        "order by pt.bank";

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(this.hsql_query);
            this.bankList = query.list();



        }catch (Exception e)
        {
            e.getLocalizedMessage();
        }
        session.clear();
        session.close();

    }

    public List<PivotTable> getBankList() {
        return bankList;
    }

}
