package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class PersistApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				StudentHibernate student = new StudentHibernate();
				student.setSid(17);
				student.setSname("Kohli");
				student.setSaddress("RCB");
				student.setSage(35);
				
				session.persist(student);
				flag = true;
			}
		} catch(HibernateException e) { 
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Object saved to database");
			}else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
			
			
		}
		
	

	}

}
