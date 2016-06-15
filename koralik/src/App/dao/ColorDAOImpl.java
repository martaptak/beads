package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import App.Model.Bead;
import App.Model.Color;
import App.Model.HibernateUtil;

public class ColorDAOImpl implements ColorDAO {

	@Override
	public void addColor(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(color);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void mergeColor(Color color1, Color color2) {

		int keeperID = color1.getIdColor();
		int gonerID = color2.getIdColor();

		List<Bead> temp = new ArrayList<Bead>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM Bead as bead join fetch bead.color as c WHERE c.idColor=:id");
		temp = query.setParameter("id", gonerID).list();

		for (Bead c : temp) {
			c.getColor().setIdColor(keeperID);
			s.save(c);
		}

		s.delete(color2);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void updateColor(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(color);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void removeColor(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(color);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Color> listColors() {
		List<Color> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		/*Criteria criteria = s.createCriteria(Color.class, "color");
		criteria.createAlias("color.colorFamily", "colorFamily");
		criteria.createAlias("color.base", "base");
		criteria.createAlias("color.coatings", "coating");
		criteria.createAlias("color.synonims", "synonim");
		criteria.addOrder(Order.asc("color.colorName"));
		list = criteria.list();*/

		Query query = s.createQuery("FROM Color as color " 
				+ "join fetch color.colorFamily "
				+ "left join fetch color.base "
				+ "left join fetch color.coatings "
				+ "left join fetch color.synonims");
		
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = query.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Color> listColorsByName(String name) {
		Session s = HibernateUtil.openSession();
		List<Color> result = new ArrayList<Color>();
		s.beginTransaction();
		result = s.createCriteria(Color.class).add(Property.forName("colorName").like(name, MatchMode.ANYWHERE))
				.addOrder(Order.asc("colorName")).list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Color> listColorByCode(String code) {
		Session s = HibernateUtil.openSession();
		List<Color> result = new ArrayList<Color>();
		s.beginTransaction();
		result = s.createCriteria(Color.class).add(Property.forName("colorCode").like(code, MatchMode.ANYWHERE))
				.addOrder(Order.asc("colorCode")).list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Color> listColorByFamily(int id) {
		Session s = HibernateUtil.openSession();
		List<Color> result = new ArrayList<Color>();
		s.beginTransaction();
		/*
		 * result = s .createQuery(
		 * "FROM Color as color join fetch color.colorFamily as family WHERE family.idColorFamily =:id"
		 * ) .setParameter("id", id).list();
		 */

		Criteria criteria = s.createCriteria(Color.class, "color");
		criteria.createAlias("color.colorFamily", "colorFamily");
		criteria.add(Restrictions.eq("colorFamily.idColorFamily", id));
		criteria.addOrder(Order.asc("color.colorName"));
		result = criteria.list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

}
