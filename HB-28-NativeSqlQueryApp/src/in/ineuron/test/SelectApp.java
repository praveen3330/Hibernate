package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.util.HibernateUtil;

public class SelectApp {
	
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			NativeQuery<Object[]> nativeQuery = session.createSQLQuery("SELECT * FROM insurancepolicy WHERE tenure >= ? AND tenure <= ?");
			
			//setting the parameter
			nativeQuery.setParameter(1, 15);
			nativeQuery.setParameter(2, 30);
			
			//Executing to get the resultset
			List<Object[]> policies = nativeQuery.getResultList();
			
			//processing the result
			System.out.println("PID\tPNAME\tPTYPE\tPTENURE");
			policies.forEach(row -> {
				for(Object obj : row) {
					System.out.print(obj + "\t");
				}
				System.out.println();
			});
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
