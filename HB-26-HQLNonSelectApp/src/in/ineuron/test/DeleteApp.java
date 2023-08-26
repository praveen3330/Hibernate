package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.util.HibernateUtil;

public class DeleteApp {
    
	@SuppressWarnings({"rawtypes"})
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		int count = 0;
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			//prepare query object to hold HQL
			Query query = session.createQuery("DELETE FROM in.ineuron.model.Product WHERE pid = :id");
			
			//set the Parameter values
			query.setParameter("id", 50);
			
			//execute the query
			count = query.executeUpdate();
			flag = true;
			
		}catch(HibernateException he) {
			he.printStackTrace();
			flag = false;
		}finally {
			if(flag) {
				transaction.commit();
				System.out.println("No of rows affected is :: " + count);
			}else {
				transaction.rollback();
				System.out.println("No of rows affected is :: " + count);
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
