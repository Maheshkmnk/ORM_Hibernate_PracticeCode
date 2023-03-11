package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.mypackage.model.PersonInfo;
import in.mypackage.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;

		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				int id = 1;
				PersonInfo personInfo = session.get(PersonInfo.class, id);
				if (personInfo != null) {
					System.out.println(personInfo);
				}else {
					System.out.println("Record not available for the given id:: "+id);
				}
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
