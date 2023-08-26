package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		Session session = null;
        try {
        	session = HibernateUtil.getSession();
        	
        	//prepare Query object to hold HQL
        	Query<Product> query = session.createQuery("FROM in.ineuron.model.Product"); //select * from product;
        	
        	//Execute the query
        	List<Product> products = query.list();
        	
        	//process the list object
        	products.forEach(System.out::println);
        	
        } catch (HibernateException he) {
        	he.printStackTrace();
        } finally {
        	HibernateUtil.closeSession(session);
        	HibernateUtil.closeSessionFactory();
        }
	}
}
