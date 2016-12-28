package aosivt.server.DBPack.UpsertCity;

import aosivt.server.Entity.City;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class UpsertCityObj {

    private City city = new City();

    public UpsertCityObj(String _nameCity)
    {
        city = checkExistBank(_nameCity);
    }

    private City checkExistBank(String _nameCity)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(City.class);
        criteria.add(Restrictions.eq("nameCity",_nameCity));
        city = (City) criteria.uniqueResult();
        session.clear();
        session.close();
        if (city==null){
            city = new City();
            city.setNameCity(_nameCity);
            city.setCityId(insertCity(city));
        }
        return city;
    }

    private Long insertCity(City _city)
    {
        Long result_id;
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(_city);
            transaction.commit();
            result_id = _city.getCityId();
            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;
    }

    public City getCity() {
        return city;
    }
}
