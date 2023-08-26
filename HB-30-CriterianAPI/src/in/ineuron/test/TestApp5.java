package in.ineuron.test;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;


public class TestApp5 {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
            session = HibernateUtil.getSession();
			
	        Criteria criteria = session.createCriteria(Product.class); //HQL=> from in.ineuron.model.Product
		   
	        ProjectionList list = Projections.projectionList();
	        list.add(Projections.property("pname"));
	        list.add(Projections.property("pquantity"));
	        
	        criteria.setProjection(list); //HQL=> SELECT pname,pquantity FROM in.ineuron.model.Product
	        
	        Criterion cond1 = Restrictions.between("pprice",65000,10000);
	        Criterion cond2 = Restrictions.in("pname", "fossil","tissot","armani");
	        Criterion cond3 = Restrictions.ilike("pname", "f%");
	        
	        Criterion finalCondtion = Restrictions.or(Restrictions.and(cond1, cond2),cond3);
	        
	        criteria.add(finalCondtion);
	        
	        Order order = Order.asc("pname");
	        
	        criteria.addOrder(order); //HQL => from in.ineuron.model.Product where pprice>=10000 and price<=60000 orderby asc(pname)`
	       
	        List<Object[]> product = criteria.list();
	        System.out.println("PNAME\tPQUANTITY");
	        product.forEach(row->{
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
