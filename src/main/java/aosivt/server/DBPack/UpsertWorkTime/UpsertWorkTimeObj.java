package aosivt.server.DBPack.UpsertWorkTime;

import aosivt.server.Entity.WorkTime;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class UpsertWorkTimeObj {

    private WorkTime workTime = new WorkTime();

    public UpsertWorkTimeObj(String _workTime)
    {
        workTime = checkExistWorkTime(_workTime);
    }

    private WorkTime checkExistWorkTime(String _workTime)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(WorkTime.class);
        criteria.add(Restrictions.eq("workTime",_workTime));
        workTime = (WorkTime) criteria.uniqueResult();
        session.clear();
        session.close();
        if (workTime==null){
            workTime = new WorkTime();
            workTime.setWorkTime(_workTime);
            workTime.setWorktimeId(insertWorkTime(workTime));
        }
        return workTime;
    }

    private Long insertWorkTime(WorkTime _workTime)
    {
        Long result_id;
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(_workTime);
            transaction.commit();
            result_id = _workTime.getWorktimeId();
            session.clear();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return result_id;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }
}
