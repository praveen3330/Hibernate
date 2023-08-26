package in.ineuron.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Product;
import in.ineuron.util.NxtWaveHibernateUtil;
import in.ineuron.util.IneuronHibernateUtil;

public class TransferDaoImpl implements ITransferDao {

	@SuppressWarnings("finally")
	@Override
	public String transferProductById(Integer id) {

		Session ineuronSession = IneuronHibernateUtil.getSession();
		Session nxtwaveSession = NxtWaveHibernateUtil.getSession();
		Integer idValue = 0;
		Boolean flag = false;

		Transaction mysqlTransaction = null;

		Product product = ineuronSession.get(Product.class, id);

		if (product == null) {
			return "Record not available for copying....";
		} else {

			try {
				mysqlTransaction = nxtwaveSession.beginTransaction();
				idValue = (Integer) nxtwaveSession.save(product);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (flag) {
					mysqlTransaction.commit();
					return "Data copied from oracle to mysql with the id :: " + idValue;
				} else {
					mysqlTransaction.rollback();
					return "Data not copied from oracle to mysql with the id :: " + idValue;
				}
			}
		}

	}

}
