package ca.wednesdaypc.lnf.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.wednesdaypc.lnf.beans.User;

public class DAO {
	
	private SessionFactory sessionFactory = new Configuration()
	.configure("ca/sheridancollege/config/hibernate.cfg.xml")
	.buildSessionFactory();
	
	public boolean createAcct(String username, String password, String email) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			User u = new User(username, password, email);
			session.save(u);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}