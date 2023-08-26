package in.ineuron.test;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class LoadAndMergeApp {

	public static void main(String[] args) throws IOException {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student student1 = null;
		Student student2 = null;
		Student student3 = null;
		
		try {
			session = HibernateUtil.getSession();
			student1 = session.get(Student.class, 2); //L1-cache
			System.out.println("After Loading the data into L1-cache :: " + student1);
			
			if(session != null) {
				transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				student2 = new Student();
				student2.setSid(2);
				student2.setSaddress("MI");
				student2.setSname("SuryaKumarYadav");
				student2.setSage(32);
				
				student3 = (Student) session.merge(student2);
				System.out.println("After merging in L1-cache :: " + student3);
				System.out.println(student1.hashCode() + ":: " + student2.hashCode() + ":: " + student3.hashCode());
				flag = true;
			}
		} catch(HibernateException e) { 
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(flag) {
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
