package aosivt.server.DBPack.UpsertAddress;

import aosivt.server.Entity.Address;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class UpsertAddressObj {

    private Address address = new Address();

    public UpsertAddressObj(String _address)
    {
        address = checkExistBank(_address);
    }

    private Address checkExistBank(String _address)
    {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("pointAddress",_address));
        address = (Address) criteria.uniqueResult();
        session.clear();
        session.close();
        if (address==null){
            address = new Address();
            address.setPointAddress(_address);
            address.setAddressId(insertAddrees(address));
        }
        return address;
    }

    private Long insertAddrees(Address _address)
    {
        Long result_id;
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(_address);
            transaction.commit();
            result_id = _address.getAddressId();
            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;
    }

    public Address getAddress() {
        return address;
    }

}
