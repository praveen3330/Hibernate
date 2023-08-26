package in.ineuron.test;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;


public class TestApp {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
	        Criteria criteria = session.createCriteria(Product.class); //HQL=> from in.ineuron.model.Product
		   
	        List<Product> product = criteria.list();
	        product.forEach(System.out::println);
	        
		}catch(HibernateException he) {
			he.printStackTrace();
			
		}finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
