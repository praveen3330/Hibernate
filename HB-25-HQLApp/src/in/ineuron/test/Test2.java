package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Test2 {

	public static void main(String[] args) {
		Session session = null;
        try {
        	session = HibernateUtil.getSession();
        	
        	//prepare Query object to hold HQL
        	Query<Product> query = session.createQuery("FROM in.ineuron.model.Product WHERE pname IN (:prod1,:prod2)"); 
        	//select * from product where pname in("fossil","tissot");
        	
        	//set values to named parameter
        	query.setParameter("prod1","fossil");
        	query.setParameter("prod2", "tissot");
        	
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
