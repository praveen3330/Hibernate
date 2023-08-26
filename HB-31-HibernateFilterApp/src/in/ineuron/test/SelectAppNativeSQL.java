package in.ineuron.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class SelectAppNativeSQL {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			Filter filter = session.enableFilter("FILTER_BANK_ACCOUNT_STATUS");
			filter.setParameter("accType1", "blocked");
			filter.setParameter("accType2", "closed");

			NativeQuery<BankAccount> query = session.createSQLQuery("SELECT * FROM bankaccount where balance>=:amt");
			query.setParameter("amt", 25000);
		    query.addEntity(BankAccount.class);

			List<BankAccount> account = query.list();
			account.forEach(System.out::println);

			System.out.println();

			session.disableFilter("FILTER_BANK_ACCOUNT_STATUS");
			Query<BankAccount> query1 = session.createQuery("from in.ineuron.model.BankAccount where balance>=:amt");
			query1.setParameter("amt", 25000);

			List<BankAccount> account1 = query1.list();
			account1.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
