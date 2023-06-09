package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.Student3;
import in.mypackage.util.HibernateUtil;

public class LoadandMergeApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student3 std1 = null;
		Student3 std2 = null;
		Student3 std3 = null;

		try {
			session = HibernateUtil.getSession();
			std1 = session.get(Student3.class, 1);// L1-cache
			System.out.println("After loading the data into L1-cache :: "+std1);

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				std2 = new Student3();
				std2.setSid(5);
				std2.setSaddress("MI");
				std2.setSname("Suryakumaryadav");
				std2.setSage(32);
				
				session.save(std2);//throws HibernateException
				std3 = (Student3) session.merge(std2);
				System.out.println("After merging in L1Cache :: "+std3);
				System.out.println(std1.hashCode() + ":: " + std2.hashCode() + ":: " + std3.hashCode());
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated to database....");
			} else {
				transaction.rollback();
				System.out.println("Object not updated to database...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
