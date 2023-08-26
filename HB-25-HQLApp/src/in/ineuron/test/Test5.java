package in.ineuron.test;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Test5 {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Product> query = session.createQuery("FROM in.ineuron.model.Product WHERE pid=:id");

			System.out.print("Enter the id of the product to be fetched :: ");
			int id = new Scanner(System.in).nextInt();
			
			
			// Set values to Named Parameter
			query.setParameter("id", id);

			// Execute the query(select * from product where pid = ?)
			List<Product> products = query.list();
			System.out.println(products.size());

			// process the List object
			if (!products.isEmpty()) {
				Product product = products.get(0);
				System.out.println(product);
			} else {
				System.out.println("Record not avaialble for the given id:: " + id);
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
