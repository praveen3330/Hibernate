package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class SelectApp3 {
	
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
			//NativeQuery<InsurancePolicy> nativeQuery = session.createSQLQuery("SELECT * FROM insurancepolicy WHERE tenure >= ? AND tenure <= ?");
			//nativeQuery.setParameter(1, 15);
			//nativeQuery.setParameter(2, 30);
			
			NativeQuery<Object[]> nativeQuery = session.createSQLQuery("SELECT policyid,policyname,tenure FROM insurancepolicy WHERE tenure >=:max AND tenure <=:min");
			
			//setting the parameter
			nativeQuery.setParameter("max", 15);
			nativeQuery.setParameter("min", 30);
			
			//Binding the datatype of output parameters
			nativeQuery.addScalar("POLICYID", StandardBasicTypes.INTEGER);
			nativeQuery.addScalar("POLICYNAME", StandardBasicTypes.STRING);
			nativeQuery.addScalar("TENURE", StandardBasicTypes.INTEGER);
			
			//Executing to get the resultset
			List<Object[]> policies = nativeQuery.getResultList();
			
			//processing the result
			policies.forEach(row->{
				for(Object object : row) {
					System.out.print(object + "\t");
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
