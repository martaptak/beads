package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import app.model.Finish;
import app.model.HibernateUtil;

public class FinishDAOImpl implements FinishDAO {

	@Override
	public void addFinish(Finish finish) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(finish);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Finish> listFinishes() {
		List<Finish> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		//list = s.createQuery("FROM Finish").list();

		Criteria criteria = s.createCriteria(Finish.class);
		criteria.addOrder(Order.asc("nameFinish"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeFinish(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Finish c = s.load(Finish.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void updateFinish(Finish finish) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(finish);
		s.getTransaction().commit();
		s.close();

	}

}
