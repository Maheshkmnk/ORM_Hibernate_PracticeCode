package in.mypackage.controller;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.StdTab;
import in.mypackage.util.HibernateUtil;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			if(session != null)
				transaction = session.beginTransaction();
			
			StdTab stdTab = new StdTab();
			
			stdTab.setSaddress("kkd");
			stdTab.setSage(26);
			stdTab.setSid(10);
			stdTab.setSname("kmnk");
			
			session.save(stdTab);
			flag = true;
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag) {			
				transaction.commit();
				System.out.println("Object saved to db...");
				//System.in.read();
			}else {
				transaction.rollback();
				System.out.println("Object not saved to db...");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
