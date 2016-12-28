package aosivt.server.DBPack.UpsertBank;

import aosivt.server.Entity.Bank;
import aosivt.server.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by oshchepkovayu on 26.12.16.
 */
public class UpsertBankObj {

    private Bank bank = new Bank();

    public UpsertBankObj(String _nameBank)
    {
        bank = checkExistBank(_nameBank);
    }

    private Bank checkExistBank(String _nameBank)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();

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

    public Bank getBank() {
        return bank;
    }
}
