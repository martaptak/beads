package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import App.Model.Beads;
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

		List<Beads> temp = new ArrayList<Beads>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM Beads as bead join fetch bead.color as c WHERE c.idColor=:id");
		temp = query.setParameter("id", gonerID).list();

		for (Beads c : temp) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Color> listColors() {
		List<Color> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("FROM Color as color join fetch color.colorFamily").list();
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
		result = s
				.createQuery(
						"FROM Color as color join fetch color.colorFamily as family WHERE family.idColorFamily =:id")
				.setParameter("id", id).list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

}
