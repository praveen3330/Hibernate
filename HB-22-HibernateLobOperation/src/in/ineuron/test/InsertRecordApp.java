package in.ineuron.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.JobSeeker;


import in.ineuron.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		byte[] jsPhoto = null;
		char[] jsResume = null;
		File f = null;
		
		//logic for copying the image data to byte[]
		try (FileInputStream fis = new FileInputStream("E:\\ineuron\\images\\nitin.JPG")) {
			jsPhoto = new byte[fis.available()];
			fis.read(jsPhoto);
		}
		
	    //logic for copying the resume data to character array
		try {
			f = new File("E:\\ineuron\\images\\history.txt");
			try (FileReader fr = new FileReader(f)){
				jsResume = new char[(int) f.length()];
				fr.read(jsResume);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				JobSeeker seeker = new JobSeeker();
				seeker.setJsName("Harish");
				seeker.setJsAddress("Kurnool");
				seeker.setJsPhoto(jsPhoto);
				seeker.setJsResume(jsResume);
				id = (Integer)session.save(seeker);
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
