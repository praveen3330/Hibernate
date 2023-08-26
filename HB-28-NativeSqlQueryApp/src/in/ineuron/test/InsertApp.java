package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.util.HibernateUtil;

public class InsertApp {
	
	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		int rowAffected = 0;
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			NativeQuery<Object[]> nativeQuery = session.createSQLQuery("INSERT INTO insurancepolicy(policyid,policyname,policytype,tenure)values(?,?,?,?)");
			
			//setting the parameter
			nativeQuery.setParameter(1, 7);
			nativeQuery.setParameter(2, "prudential");
			nativeQuery.setParameter(3, "quareterly");
			nativeQuery.setParameter(4, 24);
			
			//Executing to get the resultset
			rowAffected = nativeQuery.executeUpdate();
			
			flag = true;
			
		}catch(HibernateException he) {
			he.printStackTrace();
			flag = false;
		}finally {
			if(flag) {
				transaction.commit();
				System.out.println("Record inserted is :: " + rowAffected);
			}else {
				transaction.rollback();
				System.out.println("Record inserted is :: " + rowAffected);
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
