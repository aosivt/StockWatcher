package aosivt.server;

import aosivt.server.Entity.Bank;
import aosivt.server.Entity.Banks;
import aosivt.server.Entity.Point;
import aosivt.server.XmlGetter.StructureXml;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oshchepkovayu on 22.12.16.
 */
public class WorkingWithDB {
    private List<Banks> xmlStrgin;
    private List<String> xmlStrginInput;
    private String hsql_query;
//    private List<String> xmlStrgin = new ArrayList<String>();
    private List<StructureXml> structureXmlList ;

    private List<Bank> bankList ;

    WorkingWithDB()
    {
        this.hsql_query = "select b.name,p.city,p.address,p.time " +
                "from Bank b inner join Point p on b.bankId=p.bankId";

        this.bankList = new ArrayList<Bank>();
        selectBankList();
    }

    WorkingWithDB(List<Banks> _xmlStrgin)
    {
        this.structureXmlList = new ArrayList<StructureXml>();
        this.xmlStrgin = new ArrayList<Banks>();
        this.xmlStrgin = _xmlStrgin;
    }

//    public WorkingWithDB(List<String> _xmlStrginInput)
//    {
//        this.xmlStrginInput = _xmlStrginInput;
//    }
//
//
    public boolean EnterPointFromDB()
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
            fieldingDB(bankPoints);
        }
        else
        {

        }
        return true;
    }

    private boolean convertXml()
    {

        if (this.xmlStrgin.size()<0) return false;
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

            }

        }

        return true;
    }

    private void fieldingDB(Map<Bank,List<Point>> _input_data)
    {

        try {


            Bank bank = null;
            Point point = null;
            List<Point> pointList = null;

            for (Bank bankFromMap : _input_data.keySet())
            {
                bank = checkExistBank(bankFromMap.getName());
                    pointList = bank.getPoint();
                    for (Point pointFromMap : _input_data.get(bankFromMap)) {
                        point = checkExistPoint(
                                pointFromMap.getCity(),
                                pointFromMap.getAddress(),
                                pointFromMap.getTime(),
                                bank
                                );
                        if (pointList.indexOf(point) < 0){
                            pointList.add(point);
                        }

                    }
                    if (!bank.getPoint().equals(pointList)) {
                        bank.setPoint(pointList);
                        insertBank(bank);
                    }

                    pointList = null;
                    point = null;
                    bank = null;


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private Bank checkExistBank(String _nameBank)
    {
        Bank bank = null;
                Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Bank.class);
        criteria.add(Restrictions.eq("name",_nameBank));
        bank = (Bank) criteria.uniqueResult();
        session.clear();
        session.close();
        if (bank==null){
            bank = new Bank();
            bank.setName(_nameBank);
            bank.setBankId(insertBank(bank));
        }
        return bank;
    }
    private Point checkExistPoint(String _city,String _address,String _time,Bank _bank)
    {
        Point point = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Point.class);
        criteria.add(Restrictions.eq("city",_city));
        criteria.add(Restrictions.eq("address",_address));
        criteria.add(Restrictions.eq("time",_time));
        point = (Point) criteria.uniqueResult();
        session.clear();
        session.close();
        if (point==null){
            point = new Point();
            point.setCity(_city);
            point.setAddress(_address);
            point.setTime(_time);
//            point.setBankId(_bank.getBankId());
            point.setBank(_bank);
            point.setCityId(insertPoint(point));
        }
        return point;
    }
    private Long insertBank(Bank _bank)
    {
        Long result_id;
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(_bank);
            transaction.commit();
            result_id = _bank.getBankId();
            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;
    }
    private Long insertPoint(Point _point)
    {
        Long result_id;
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(_point);
            transaction.commit();
            result_id = _point.getBankId();
            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;
    }

    private void selectBankList()
    {
        Session session = null;
        /*this.hsql_query =
                "select b.name,p.city,p.address,p.time " +
                "from Bank b , Point p where b.bankId = p.bankId";*/
        this.hsql_query =
                "select b " +
                        "from Bank b inner join b.point " +
                        "order by b.name,  b.bankId";
//                        "group by b.name,  b.bankId";
// ,Point p         join p on b.bankId = p.bankId
try {
    session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery(this.hsql_query);
    List<Object[]> listResult = query.list();

//        Transaction transaction = session.beginTransaction();
//
//        Criteria criteria = session.createCriteria(Bank.class);
//
//        this.bankList = (List<Bank>) criteria.setProjection(
//                Projections.groupProperty("name")).list();

}catch (Exception e)
{
    e.getLocalizedMessage();
}
        session.clear();
        session.close();

    }
    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }


}
