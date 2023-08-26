package in.ineuron.test;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class InsertApp {

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
					Student student = new Student();
					student.setSname("sachin");
					student.setSage(35);
					student.setSaddress("RCB");
					
					session.save(student);
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
