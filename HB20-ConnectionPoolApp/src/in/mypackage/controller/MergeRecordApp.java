package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.Student3;
import in.mypackage.util.HibernateUtil;

public class MergeRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student3 std = null;

		try {

			Student3 student = new Student3();
			student.setSid(5);
			student.setSname("SKY");
			student.setSaddress("MI");
			student.setSage(31);

			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				std = (Student3) session.merge(student);
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated to database...."+std);
			} else {
				transaction.rollback();
				System.out.println("Object not updated to database..."+std);
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
