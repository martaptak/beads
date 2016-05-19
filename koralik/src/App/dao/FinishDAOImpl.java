package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import App.Model.Finish;
import App.Model.HibernateUtil;

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
		List<Finish> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("FROM Finish").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeFinish(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Finish c = (Finish) s.load(Finish.class, id);
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
