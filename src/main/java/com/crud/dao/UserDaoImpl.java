package com.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.crud.model.User;
import com.crud.util.HibernateConf;

public class UserDaoImpl implements UserDao {
	private SessionFactory factory = HibernateConf.getFactory();
	
	@Override
	public void saveUser(User user) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
	
	@Override
	public User getUserById(long id) {
		Transaction transaction = null;
		User user = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, id);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return user;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Transaction transaction = null;
		List<User> users = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			users = session.createQuery("from User").list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return users;
	}
	
	@Override
	public void updateUser(User user) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
	
	@Override
	public void deleteUserById(long id) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			User user = session.get(User.class, id);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
}