package in.ineuron.test;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.BankAccount;


import in.ineuron.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Boolean flag = false;
		Long id = 3L;
		Transaction transaction = null;
		BankAccount account = null;

		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				account = session.get(BankAccount.class, id);
				System.out.println("Before the modification :: " + account);
    
				if(account != null) {
					transaction = session.beginTransaction();
					account.setBalance(account.getBalance() + 10000);
					flag = true;
					
				}else {
					System.out.println("Record not available for the given id :: " + id);
					System.exit(0);
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("object updated to database...");
				System.out.println("Account Opening Date :: " + account.getOpeningDate());
				System.out.println("Account Lastly modified :: " + account.getLastUpdated());
				System.out.println("Account version count :: " + account.getCount());
			} else {
				transaction.rollback();
				System.out.println("object not updated to database...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}
}
