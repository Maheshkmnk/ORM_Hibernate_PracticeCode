package in.mypackage.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.build.AllowSysOut;

import in.mypackage.model.Product;
import in.mypackage.util.HibernateUtil;


public class SelectApp2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = null;

		try {
			session = HibernateUtil.getSession();
			
			Criteria criteria = session.createCriteria(Product.class);
			
			Criterion condition1 = Restrictions.ge("price", 0);
			Criterion condition2 = Restrictions.le("price", 5000);
			
			ProjectionList list = Projections.projectionList();
			list.add(Projections.property("pname"));
			list.add(Projections.property("qty"));
			
			criteria.setProjection(list);
			
			criteria.add(condition1);
			criteria.add(condition2);
			
			Order order = Order.asc("pname");
			criteria.addOrder(order);
		
			
			List<Object[]> products = criteria.list();
			System.out.println("ProductName\tqty");
			products.forEach(row->{
				for(Object obj : row) {
					System.out.print(obj+"\t\t");
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
