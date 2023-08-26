package in.ineuron.test;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class DeleteRecordApp2 {

	public static void main(String[] args) throws IOException {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		
		
		try {
			session = HibernateUtil.getSession();
			int id = 18;
		    StudentHibernate studentHibernate = session.get(StudentHibernate.class, id);
			if(session != null) {
				transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				if(studentHibernate != null) {
					
					session.delete(studentHibernate);
					flag = true;
				}
				else {
					System.out.println("Record not available for deletion:: " + id);
				}	
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
