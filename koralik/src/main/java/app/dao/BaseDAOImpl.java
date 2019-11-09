package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import app.model.Base;
import app.model.HibernateUtil;

public class BaseDAOImpl implements BaseDAO {

	@Override
	public void addBase(Base base) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(base);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateBase(Base base) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(base);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void removeBase(Base base) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(base);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Base> basesList() {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		List<Base> list;
		Criteria criteria = s.createCriteria(Base.class);
		criteria.addOrder(Order.asc("nameBase"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();

		return list;
	}

}
