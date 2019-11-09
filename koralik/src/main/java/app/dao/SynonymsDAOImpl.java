package app.dao;

import java.util.List;

import app.model.Synonyms;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import app.model.Color;
import app.model.HibernateUtil;

public class SynonymsDAOImpl implements SynonymsDAO {

	@Override
	public void addSynonym(Synonyms synonym) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(synonym);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateSynonym(Synonyms synonym) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(synonym);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void removeSynonym(Synonyms synonym) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(synonym);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Synonyms> synonymsList(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		List<Synonyms> list;
		Criteria criteria = s.createCriteria(Synonyms.class, "synonym");
		criteria.add(Restrictions.eq("synonim.color", color));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();

		return list;
	}

}
