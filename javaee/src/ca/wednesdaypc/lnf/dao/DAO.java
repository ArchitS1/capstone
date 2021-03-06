package ca.wednesdaypc.lnf.dao;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import ca.wednesdaypc.lnf.beans.User;
import ca.wednesdaypc.lnf.netspec.JsonResponse;
import ca.wednesdaypc.lnf.netspec.Profile;

public class DAO {
	
	private SessionFactory sessionFactory = new Configuration()
	.configure("ca/sheridancollege/config/hibernate.cfg.xml")
	.buildSessionFactory();
	
	public int createAcct(String username, String password, String email) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			User u = new User(username, password, email);
			session.save(u);
			
			session.getTransaction().commit();
			session.close();
			
			return JsonResponse.CODE_NOMINAL;
		} catch (Exception e) {
			if (e instanceof PersistenceException && e.getCause() instanceof ConstraintViolationException) {
				return JsonResponse.CODE_DUPE_USERNAME;
			} else {
				return JsonResponse.CODE_DB_ERROR;
			}
		} finally {
			if (session != null) session.close();
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
	
	public JsonResponse viewAccount(String username) {
		JsonResponse jr = new JsonResponse();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.getNamedQuery("User.viewAccount");
			query.setParameter("username", username);
			
			List<User> rs = query.getResultList();
			
			if (rs.isEmpty()) {
				jr.resultCode = JsonResponse.CODE_DB_ERROR;
				session.close();
				return jr;
			}
			
			Profile p = new Profile(rs.get(0));
			jr.payload = p;
			session.close();
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			jr.resultCode = JsonResponse.CODE_DB_ERROR;
			return jr;
		}
	}
}