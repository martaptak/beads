package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import App.Model.Color;
import App.Model.HibernateUtil;
import App.Model.Synonims;

public class SynonimsDAOImpl implements SynonimsDAO {

	@Override
	public void addSynonim(Synonims synonim) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(synonim);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateSynonim(Synonims synonim) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(synonim);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void removeSynonim(Synonims synonim) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(synonim);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Synonims> synonimsList(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		List<Synonims> list = new ArrayList<>();
		Criteria criteria = s.createCriteria(Synonims.class, "synonim");
		criteria.add(Restrictions.eq("synonim.color", color));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		
		return list;
	}

}
