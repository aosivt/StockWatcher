package aosivt.server.DBPack.UpsertPivotTable;

import aosivt.server.Entity.*;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class UpsertPivotTableObj {

    private PivotTable pivotTable = new PivotTable();
    public UpsertPivotTableObj(Bank _bank, City _city, Address _address, WorkTime _workTime)
    {
    pivotTable = checExistPivotTable(_bank, _city, _address, _workTime);
    }

    private PivotTable checExistPivotTable (Bank _bank, City _city, Address _address, WorkTime _workTime)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(PivotTable.class);
        criteria.add(Restrictions.eq("bank",_bank));
        criteria.add(Restrictions.eq("city",_city));
        criteria.add(Restrictions.eq("address",_address));
        criteria.add(Restrictions.eq("workTime",_workTime));
        pivotTable = (PivotTable) criteria.uniqueResult();
        session.clear();
        session.close();
        if (pivotTable==null){
            pivotTable = new PivotTable();
            pivotTable.setBank(_bank);
            pivotTable.setCity(_city);
            pivotTable.setAddress(_address);
            pivotTable.setWorkTime(_workTime);
            pivotTable.setPivotId(insertPivotTable());

        }
        return pivotTable;
    }
    private Long insertPivotTable()
    {
        Long result_id;
        Session session = null;



        try {

            session  = HibernateUtil.getSessionFactory().openSession();

            PivotTable pivotTableTemp = (PivotTable) session.merge(this.pivotTable);
            Transaction transaction
                     = session.beginTransaction();
//
//
//            session.clear();
//            session.flush();

            session.saveOrUpdate(pivotTableTemp);
            result_id = pivotTableTemp.getPivotId();
            transaction.commit();


            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;

    }

    public PivotTable getPivotTable() {
        return pivotTable;
    }
}
