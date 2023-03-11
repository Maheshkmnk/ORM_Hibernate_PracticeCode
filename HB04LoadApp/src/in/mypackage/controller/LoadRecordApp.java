package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.mypackage.model.Student1;
import in.mypackage.util.HibernateUtil;

public class LoadRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		int id = 99;
		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				Student1 student = session.load(Student1.class, id);
				
				if (student != null) {
					System.out.println("Student id is      :: " + student.getSid());
					
					System.in.read();
					
					
					System.out.println("STUDENT NAME IS    :: " + student.getSname());
					System.out.println("STUDENT AGE IS     :: " + student.getSage());
					System.out.println("STUDENT ADDRESS IS :: " + student.getSaddress());
				} else
					System.out.println("Record not found for the given id :: " + id);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
