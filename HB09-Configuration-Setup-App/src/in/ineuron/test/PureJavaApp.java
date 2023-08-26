package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.StudentHibernate;

public class PureJavaApp {

	public static void main(String[] args) {
		int id = 7;
		Session session = null;
		SessionFactory buildSessionFactory = null;
		
		try {
			Configuration configuration = new Configuration();
			
			// Setting the properties for configuration object using pure java code
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/ineuron");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "Harish16#");

			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			configuration.setProperty("hibernate.show_sql", "true");
			configuration.setProperty("hibernate.format_sql", "true");
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			
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
