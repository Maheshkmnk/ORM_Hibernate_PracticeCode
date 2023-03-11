package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.InsurancePolicy;
import in.mypackage.util.HibernateUtil;

public class ClientApp1 {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		Boolean flag = false;

		session = HibernateUtil.getSession();
		try {

			transaction = session.beginTransaction();
			
			InsurancePolicy policy = session.get(InsurancePolicy.class, 1,LockMode.UPGRADE_NOWAIT);
			System.out.println(policy);
			
			Thread.sleep(30000);//sleep for 30secs
			
			policy.setTenure(30);
			flag =true;

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
