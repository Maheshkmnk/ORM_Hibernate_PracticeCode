package in.mypackage.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.mypackage.model.Student1;

public class TestApp {

	public static void main(String[] args) throws Exception {

		SessionFactory sessionFactory = null;
		Session session = null;
		int id = 1;

		try {

			Configuration configuration = new Configuration();

			// Setting the properties for configuration object using pure java code
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql:///octbatch");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "root123");

			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			configuration.setProperty("hibernate.show_sql", "true");
			configuration.setProperty("hibernate.format_sql", "true");
			configuration.setProperty("hibernate.hbm2ddl.auto", "create");

			configuration.configure();

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
