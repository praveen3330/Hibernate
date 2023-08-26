package in.ineuron.test;

import java.util.Scanner;

import in.ineuron.dao.ITransferDao;
import in.ineuron.dao.TransferDaoImpl;
import in.ineuron.util.NxtWaveHibernateUtil;
import in.ineuron.util.IneuronHibernateUtil;

public class InteractWithMultipleDB {

	public static void main(String[] args) throws Exception {

		ITransferDao dao = new TransferDaoImpl();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the id to move the data : ");
		int id = scan.nextInt();
		System.out.println(dao.transferProductById(id));

		IneuronHibernateUtil.closeSessionFactory();
		NxtWaveHibernateUtil.closeSessionFactory();

	}
}

