package in.ineuron.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class SelectAppUsingCriterianApi {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		try {
			session = HibernateUtil.getSession();

			Filter filter = session.enableFilter("FILTER_BANK_ACCOUNT_STATUS");
			filter.setParameter("accType1", "blocked");
			filter.setParameter("accType2", "closed");

			Criteria criteria = session.createCriteria(BankAccount.class);
			Criterion cond = Restrictions.ge("balance", 25000);
			criteria.add(cond);

			List<BankAccount> account = criteria.list();
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
