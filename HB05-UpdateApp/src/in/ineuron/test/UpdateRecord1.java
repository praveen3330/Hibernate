package in.ineuron.test;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class UpdateRecord1 {

	public static void main(String[] args) throws IOException {
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
					student.setSid(18); // Programmer should know that record with the id exists
					student.setSname("Virat");
					student.setSage(36);
					student.setSaddress("IND");
					
					session.update(student);
					flag = true;
			}
		} catch(HibernateException e) { 
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(flag) {
				System.in.read();
				transaction.commit();
				System.out.println("Object updated to database....");
			}else {
				transaction.rollback();
				System.out.println("Object not updated to database....");
			}
				
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();	
		}
	}
}