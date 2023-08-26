package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.ineuron.model.StudentHibernate;


public class TestApp {

	public static void main(String[] args) {
		Session session = null;
		int id = 18;

		try {
			StudentHibernate studentHibernate = session.get(StudentHibernate.class, id);

			if (studentHibernate != null) {
				System.out.println("Before updation in the table :: " + studentHibernate);

			} else {
				System.out.println("Record unavailable for the given id :: " + id);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		
		}

	}

}
