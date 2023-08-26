package in.ineuron.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class GetApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int id = 77;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				StudentHibernate studentHibernate = session.get(StudentHibernate.class, id);
				if(studentHibernate != null) {
					System.out.println(studentHibernate);
				}
				else {
					System.out.println("Record not found : " + id);
				}
			}
		} catch(HibernateException e) { 
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
			
			
		}
		
	

	}

}
