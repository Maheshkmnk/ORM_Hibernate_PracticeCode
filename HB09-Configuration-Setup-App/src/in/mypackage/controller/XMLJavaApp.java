package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.mypackage.model.Student1;

public class XMLJavaApp {

	public static void main(String[] args) throws Exception {

		SessionFactory sessionFactory = null;
		Session session = null;
		int id = 1;

		try {

			Configuration configuration = new Configuration();

			configuration.configure();//HIBERNATE will search for hibernate.cfg.xml file
			
			// provided information about mapping file
			configuration.addAnnotatedClass(Student1.class);

			sessionFactory = configuration.buildSessionFactory();

			session = sessionFactory.openSession();

			Student1 student = session.get(Student1.class, id);

			if (student != null) {
				System.out.println("Before updation in the table :: " + student);
			} else {
				System.out.println("Record available for the given id :: " + id);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}

	}

}
