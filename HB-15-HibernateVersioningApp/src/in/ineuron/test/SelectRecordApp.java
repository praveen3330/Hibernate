package in.ineuron.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.MobileCustomer;

import in.ineuron.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Boolean flag = false;
		Transaction transaction = null;
		MobileCustomer mobileCustomer = null;

		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				mobileCustomer = session.get(MobileCustomer.class, 1);
				System.out.println("Loading the object :: " + mobileCustomer);
    
				transaction = session.beginTransaction();
			    mobileCustomer.setCallerTune("CSKCSKCSK.........");
				session.update(mobileCustomer);
				flag = true;
				
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("object updated to database...");
				System.out.println("After modification :: " + mobileCustomer);
			} else {
				transaction.rollback();
				System.out.println("object not updated to database...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}
}
