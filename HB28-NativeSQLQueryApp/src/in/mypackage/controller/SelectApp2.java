package in.mypackage.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.mypackage.util.HibernateUtil;

public class SelectApp2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<Object[]> nativeQuery = session
					.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");

			//Setting the parameter
			nativeQuery.setParameter(1, 1);
			nativeQuery.setParameter(2, 5);

			//Executing to get the result
			List<Object[]> policies = nativeQuery.getResultList();
			
			//Processing the result
			System.out.println("PID\tPNAME\tPTYPE\tTENURE\tCOMPANY");
			policies.forEach(row -> {
				for (Object obj : row) {
					System.out.print(obj + "\t");
				}
				System.out.println();
			});

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
