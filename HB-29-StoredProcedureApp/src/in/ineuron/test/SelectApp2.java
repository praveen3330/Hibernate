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
 CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_Product_Details_by_id`(in id int, out name varchar(20), out price int, out quantity int)
BEGIN
      select pname, pprice, pquantity into name, price, quantity from product where pid = id;
 END
*/

public class SelectApp2 {
	
	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Integer pid = 1;
			
			//creating a procedure call object
			ProcedureCall procedureCall = session.createStoredProcedureCall("Get_Product_Details_by_id");
			
			//Binding input parameter value for procedure call object
			procedureCall.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(pid);
			procedureCall.registerParameter(2, String.class, ParameterMode.OUT);
			procedureCall.registerParameter(3, Integer.class, ParameterMode.OUT);
			procedureCall.registerParameter(4, Integer.class, ParameterMode.OUT);
			
		    //Executing the procedurecall to get the result
			String pname = (String) procedureCall.getOutputParameterValue(2);
			Integer pprice = (Integer)procedureCall.getOutputParameterValue(3);
			Integer pquantity = (Integer)procedureCall.getOutputParameterValue(4);
			
			System.out.println("PNAME\tPPRICE\tPQUANTITY");
			System.out.println(pname + "\t" + pprice + "\t" + pquantity);
		   
		}catch(HibernateException he) {
			he.printStackTrace();
			
		}finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
