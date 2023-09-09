package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.cj.xdevapi.Result;

import model.Cat;
import util.HibernateUtil;

public class CatDAO implements DAOInterface<Cat> {

	@Override
	public List<Cat> selectAll() {
		List<Cat> list = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if (sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				
				// HQL
				String hql = "from Cat";
				Query query = session.createQuery(hql);
				list = query.getResultList();
				
				
				tr.commit();
				session.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return list;
	}

	@Override
	public Cat selectById(Cat t) {
		Cat result = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if (sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				
				// HQL
				
				result = session.get(Cat.class, 1);
				
				
				tr.commit();
				session.close();
				return result;
			} 
		} catch (Exception e) {
			e.printStackTrace();	
			}
		return null;
		
	}
	
	public boolean saveOrUpdate(Cat t) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if (sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				
				// HQL
				session.saveOrUpdate(t);
				
				
				tr.commit();
				session.close();
				return true;
			} 
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return false;
	}
	
	@Override
	public boolean insert(Cat t) {
		return saveOrUpdate(t);
	}

	@Override
	public boolean update(Cat t) {
		return saveOrUpdate(t);
	}

	@Override
	public boolean delete(Cat t) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if (sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				
				// HQL
				session.delete(t);
				
				
				tr.commit();
				session.close();
				return true;
			} 
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return false;
	}
	
}
