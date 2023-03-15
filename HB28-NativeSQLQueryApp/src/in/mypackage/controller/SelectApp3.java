package in.mypackage.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import in.mypackage.util.HibernateUtil;

public class SelectApp3 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<Object[]> nativeQuery = session
					.createSQLQuery("SELECT POLICYID, POLICYNAME,COMPANY FROM INSURANCEPOLICY WHERE TENURE<=:max AND TENURE>=:min");

			//Setting the parameter
			nativeQuery.setParameter("min", 1);
			nativeQuery.setParameter("max", 5);
			
			//BINDING THE DATATYPE OF OUTPUT PARAMETERS
			nativeQuery.addScalar("POLICYID", StandardBasicTypes.INTEGER);
			nativeQuery.addScalar("POLICYNAME", StandardBasicTypes.STRING);
			nativeQuery.addScalar("COMPANY", StandardBasicTypes.STRING);
			
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
