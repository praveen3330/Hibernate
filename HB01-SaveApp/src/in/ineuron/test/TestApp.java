package in.ineuron.test;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.Student;

public class TestApp {

	public static void main(String[] args) throws IOException {
		//1. Inform Jvm to activate Hibernate
		Configuration configuration = new Configuration();
		configuration.configure();
		
		//creating a sessionFactory object to hold many other objects required for hibernate 
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		//using sessionfactory object get only one session object to perform our persistence operation
		Session session = sessionFactory.openSession();   //connection object,ORM-dialects,L1-cache,TXTManagement
		
		Transaction transaction = session.beginTransaction(); // transaction.begin()
		
		//2.Create Persistence Object
	    Student student = new Student();
	    student.setSid(10);
	    student.setSname("sachin");
	    student.setSaddress("MI");
	    student.setSage(49);
	    
	    //3.perform persistence operation using Entity/Model/POJO object
	    session.save(student);
	    
	    System.in.read();
	    
	    //4. commit the operation based on the result
	    transaction.commit();
	    
	    System.out.println("Object saved to database");
	    
	    session.close();
	    sessionFactory.close();
	}

}
