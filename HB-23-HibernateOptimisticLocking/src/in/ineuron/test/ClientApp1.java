package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class ClientApp1 {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		Boolean flag = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			InsurancePolicy policy = session.get(InsurancePolicy.class, 1);
			System.out.println(policy);
			
			Thread.sleep(30000); //sleep for 30sec
			
			policy.setTenure(30);
			flag = true;
		
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Object inserted to the database with the id :: " + id);
			}else {
				transaction.rollback();
				System.out.println("Object not inserted to the database with the id :: " + id);
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
