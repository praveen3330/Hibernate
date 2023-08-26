package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.StudentHibernate;

public class PropertiesFileApp {

	public static void main(String[] args) {
		int id = 7;
		Session session = null;
		SessionFactory buildSessionFactory = null;
		
		try {
			Configuration configuration = new Configuration();// by default HIBERNATE searches for hibernate.properties
			
			// provided information about mapping file
            configuration.addAnnotatedClass(StudentHibernate.class);
            
            buildSessionFactory = configuration.buildSessionFactory();
            
            session = buildSessionFactory.openSession();
            
            StudentHibernate studentHibernate = session.get(StudentHibernate.class, id);
            
            if(studentHibernate != null) {
            	System.out.println("Before updation in the table :: " + studentHibernate);
            }
            else {
            	System.out.println("Record available for the given id :: " + id);
            }
		}catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			buildSessionFactory.close();
		}


	}

}
