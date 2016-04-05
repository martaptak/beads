package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import App.Model.Beads;
import App.Model.HibernateUtil;

public class BeadDAOImpl implements BeadDAO {

	@Override
	public void addBead(Beads bead) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(bead);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeads() {

		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("FROM Beads").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeBeads(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Beads c = (Beads) s.load(Beads.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateBeads(Beads beads) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(beads);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Beads> listBeadsBySize(String size) {
		Session s = HibernateUtil.openSession();
		List<Beads> result = new ArrayList<Beads>();
		s.beginTransaction();
		result = s.createCriteria(Beads.class).add(Property.forName("size").like(size, MatchMode.ANYWHERE))
				.addOrder(Order.asc("size")).list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Beads> superSearch(String searchString) {
		List<Beads> result = new ArrayList<Beads>();

		String[] searchTerms = searchString.split(" ");
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Beads.class);

		for (String searchTerm : searchTerms) {
			Criterion c = Restrictions.or(Property.forName("size").like(searchTerm, MatchMode.ANYWHERE),
					Property.forName("imageUrl").like(searchTerm, MatchMode.ANYWHERE));
			criteria.add(c);
		}

		result = criteria.list();

		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsFotTable() {
		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		String sql = "from Beads as bead " + "left join fetch bead.category as c "
				+ "inner join fetch c.parentCategory " + "left join fetch bead.color";

		list = s.createQuery(sql).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Beads> listBeadsFotTable(String categoryParent,
	 * String categoryChild) { List<Beads> list = new ArrayList<>(); Session s =
	 * HibernateUtil.openSession(); s.beginTransaction();
	 * 
	 * String sql = "from Beads as bead " +
	 * "left join fetch bead.category as c " +
	 * "inner join fetch c.parentCategory " + "left join fetch bead.color " +
	 * "where c.categoryName = :categoryChild " +
	 * "and c.parentCategory.categoryName = :categoryParent";
	 * 
	 * list = s.createQuery(sql) .setParameter(categoryChild, categoryChild)
	 * .setParameter(categoryParent, categoryParent) .list();
	 * s.getTransaction().commit(); s.close(); return list; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsFotTable(String categoryParent, String categoryChild) {
		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Beads.class).createCriteria("category")
				.add(Property.forName("categoryName").like(categoryChild, MatchMode.EXACT))
				.createCriteria("parentCategory")
				.add(Property.forName("categoryName").like(categoryParent, MatchMode.EXACT));

		list = criteria.list();

		return list;
	}

}
