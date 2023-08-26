package in.ineuron.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class LoadApp {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		int id = 7;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				StudentHibernate studentHibernate = session.load(StudentHibernate.class, id);
				if(studentHibernate != null) {
					System.out.println("Student id is :: " + studentHibernate.getSid());
					System.in.read();
					
					System.out.println("Student id name :: " + studentHibernate.getSname());
					System.out.println("Student id address :: " + studentHibernate.getSaddress());
					System.out.println("Student id age :: " + studentHibernate.getSage());
					
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
