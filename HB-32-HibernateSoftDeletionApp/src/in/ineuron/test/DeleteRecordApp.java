package in.ineuron.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class DeleteRecordApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
        Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
		    transaction = session.beginTransaction();  
            
            BankAccount account = new BankAccount();
            account.setAccno(3456);
            
            session.delete(account);
            
            flag = true;
            
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Objedct status changed to closed/blocked ==> Soft deletion");
			}
			else {
				transaction.rollback();
				System.out.println("Objedct status not changed");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
