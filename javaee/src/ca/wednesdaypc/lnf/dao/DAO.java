package ca.wednesdaypc.lnf.dao;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.wednesdaypc.lnf.beans.User;
import ca.wednesdaypc.lnf.netspec.JsonResponse;

public class DAO {
	
	private SessionFactory sessionFactory = new Configuration()
	.configure("ca/sheridancollege/config/hibernate.cfg.xml")
	.buildSessionFactory();
	
	public boolean createAcct(String username, String password, String email) {
		Session session;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			User u = new User(username, password, email);
			session.save(u);
			
			session.getTransaction().commit();
			session.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int login(String username, String password) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.getNamedQuery("User.exists");
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			List<Long> resList = query.getResultList(); 
			
			if (resList.get(0) == 1) {
				return JsonResponse.CODE_NOMINAL;
			} else {
				return JsonResponse.CODE_INVALID_CREDS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.CODE_DB_ERROR;
		}
	}
}