package in.mypackage.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import in.mypackage.util.HibernateUtil;

public class SelectApp4 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<String> nativeQuery = session
					.createSQLQuery("SELECT POLICYNAME FROM INSURANCEPOLICY WHERE TENURE<=:max AND TENURE>=:min");

			//Setting the parameter
			nativeQuery.setParameter("min", 1);
			nativeQuery.setParameter("max", 5);
		
			
			//Executing to get the result
			List<String> policies = nativeQuery.getResultList();
			
			//Processing the result
			policies.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
