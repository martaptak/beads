package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import app.model.HibernateUtil;
import app.model.Store;

public class StoresDAOImpl implements StoresDAO {

	public void addStore(Store store) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(store);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Store> listStores() {
		List<Store> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Store.class);
		criteria.addOrder(Order.asc("storeName"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	public void removeStore(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Store c = s.load(Store.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	public void updateStore(Store store) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(store);
		s.getTransaction().commit();
		s.close();
	}
}
