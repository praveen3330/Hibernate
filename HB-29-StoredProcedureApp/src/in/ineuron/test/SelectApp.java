package in.ineuron.test;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

/* 
 CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_Product_Details_by_name`(in name1 varchar(20), in name2 varchar(20))
BEGIN
       select pid,pname,pprice,pquantity from product where pname in (name1,name2);
END
*/

public class SelectApp {
	
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			String product1 = "fossil";
			String product2 = "tissot";
			
			//creating a procedure call object
			ProcedureCall procedureCall = session.createStoredProcedureCall("Get_Product_Details_by_name", Product.class);
			
			//Binding input parameter value for procedure call object
			procedureCall.registerParameter(1, String.class, ParameterMode.IN).bindValue(product1);
			procedureCall.registerParameter(2, String.class, ParameterMode.IN).bindValue(product2);
			
			//Executing the store procedure to get the result
			List<Product> products = procedureCall.getResultList();
			
			//processing the result
			products.forEach(System.out::println);
		    
		   
		}catch(HibernateException he) {
			he.printStackTrace();
			
		}finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
