package in.ineuron.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.ineuron.model.StudentHibernate;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		int id = 18;

		try {
			session = HibernateUtil.getSession();
			StudentHibernate student = session.get(StudentHibernate.class, id);
			System.out.println("Before updation in the table :: " + student);

			if (student != null) {
				System.in.read();// go to DB and make the change

				session.refresh(student);

				System.out.println("After updation in the table :: " + student);

			} else {
				System.out.println("Record unavailable for the given id :: " + id);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
