package in.mypackage.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import in.mypackage.model.Product;
import in.mypackage.util.HibernateUtil;

/** 
 * CREATE DEFINER=`root`@`localhost` PROCEDURE `P_GET_PRODUCT_BY_NAME`(IN name1 VARCHAR(20), IN name2 VARCHAR(20)) 
  BEGIN 
  SELECT pid,pname,price,qty FROM
 							products WHERE pname IN (name1,name2); END$$
 
 DELIMITER ;
 *
 */
public class SelectApp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();
			
			Criteria criteria = session.createCriteria(Product.class);
			
			Criterion condition1 = Restrictions.ge("price", 0);
			Criterion condition2 = Restrictions.le("price", 5000);
			
			criteria.add(condition1);
			criteria.add(condition2);
			
			Order order = Order.asc("pname");
			criteria.addOrder(order);
		
			
			List<Product> products = criteria.list();
			
			products.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}
}
