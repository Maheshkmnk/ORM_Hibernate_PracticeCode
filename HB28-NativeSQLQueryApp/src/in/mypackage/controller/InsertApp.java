package in.mypackage.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import in.mypackage.util.HibernateUtil;

public class InsertApp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		Integer rowAffected=0 ;

		try {
			session = HibernateUtil.getSession();

			if(session != null) {
				transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				NativeQuery<String> nativeQuery = session
						.createSQLQuery("insert into insurancepolicy(policyName, policyType, tenure, company)values(?,?,?,?)");
				
				//Setting the parameter
				nativeQuery.setParameter(1, "Prudential");
				nativeQuery.setParameter(2, "quatarly");
				nativeQuery.setParameter(3, "24");
				nativeQuery.setParameter(4, "kmnk");
			
				
				//Executing to get the result
				rowAffected = nativeQuery.executeUpdate();
				
				flag=true;
			}
			

			

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			
			if(flag==true) {
				transaction.commit();
				System.out.println(rowAffected);
			}else {
				transaction.rollback();
				System.out.println("no record inserted");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
