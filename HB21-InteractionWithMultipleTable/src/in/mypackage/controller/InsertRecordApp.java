package in.mypackage.controller;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.mypackage.model.BookData;
import in.mypackage.model.CustomerDetails;
import in.mypackage.model.OrderDetails;
import in.mypackage.util.HibernateUtil;

public class InsertRecordApp {
	public static OrderDetails buy(Integer uid, Integer bid){
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setBid(bid);
		orderDetails.setUid(uid);
		
		return orderDetails;
	}
	
	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		boolean flag = false;
		BookData bookData = new BookData();
		CustomerDetails customerDetails = new CustomerDetails();
		OrderDetails orderDetails = new OrderDetails();
		try {
			session = HibernateUtil.getSession();
			if(session != null) {
				transaction = session.beginTransaction();
			}
			if(transaction != null) {
				OrderDetails buy = buy(1,1);
				
				id = (Integer) session.save(buy);
				flag=true;
			}
		}catch (HibernateException he) {
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(flag==true) {
				transaction.commit();
				System.out.println("successfully record inserted:"+id);
			}else {
				transaction.rollback();
				System.out.println("record insertion failed :"+id);
			}
		}

	}

}
