package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.ProgrammerProjId;
import in.mypackage.model.ProgrammerProjInfo;
import in.mypackage.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		ProgrammerProjId pk = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				ProgrammerProjId projId = new ProgrammerProjId();
				projId.setPid(100);
				projId.setProjId(501);

				ProgrammerProjInfo info = new ProgrammerProjInfo();
				info.setId(projId);
				info.setDeptNo(10);
				info.setPname("sachin");
				info.setProjName("IPL");

				pk = (ProgrammerProjId) session.save(info);

				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: " + pk);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
