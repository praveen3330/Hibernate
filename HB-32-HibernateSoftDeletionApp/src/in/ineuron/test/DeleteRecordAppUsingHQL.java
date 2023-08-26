package in.ineuron.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class DeleteRecordAppUsingHQL {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Transaction transaction =  null;
		Boolean flag = false;
		int rowcount = 0;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			//Query query = session.createQuery("DELETE FROM in.ineuron.model.BankAccount where accno = :no");
			Query query = session.createQuery("UPDATE in.ineuron.model.BankAccount SET status = 'closed' where accno = :no");
			query.setParameter("no", 1234);
			rowcount = query.executeUpdate();
			
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
