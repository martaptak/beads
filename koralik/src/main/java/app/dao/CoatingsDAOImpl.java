package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import app.model.Coatings;
import app.model.HibernateUtil;

public class CoatingsDAOImpl implements CoatingsDAO {

	@Override
	public void addCoating(Coatings coating) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(coating);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateCoatings(Coatings coating) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(coating);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void removeCoatings(Coatings coating) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(coating);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coatings> coatingsList() {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		List<Coatings> list;
		Criteria criteria = s.createCriteria(Coatings.class);
		criteria.addOrder(Order.asc("nameCoating"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();

		return list;
	}

}
