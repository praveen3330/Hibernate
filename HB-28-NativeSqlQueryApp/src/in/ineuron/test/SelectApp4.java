package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.Slicing;

public class SelectApp4 {
	
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
			//NativeQuery<InsurancePolicy> nativeQuery = session.createSQLQuery("SELECT * FROM insurancepolicy WHERE tenure >= ? AND tenure <= ?");
			//nativeQuery.setParameter(1, 15);
			//nativeQuery.setParameter(2, 30);
			
			NativeQuery<String> nativeQuery = session.createSQLQuery("SELECT policyname FROM insurancepolicy WHERE tenure >=:max AND tenure <=:min");
			
			//setting the parameter
			nativeQuery.setParameter("max", 15);
			nativeQuery.setParameter("min", 30);
			
			//Executing to get the resultset
			List<String> policies = nativeQuery.getResultList();
			
			//processing the result
		    policies.forEach(System.out::println);
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
