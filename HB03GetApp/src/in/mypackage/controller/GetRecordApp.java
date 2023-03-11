package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.mypackage.model.Student1;
import in.mypackage.util.HibernateUtil;

public class GetRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		int sid = 1;
		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				Student1 student = session.get(Student1.class, sid);
				if (student != null)
					System.out.println(student);
				else
					System.out.println("Record not found for the given id :: "+sid);
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
