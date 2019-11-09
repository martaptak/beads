package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import app.model.Bead;
import app.model.Category;
import app.model.Color;
import app.model.HibernateUtil;

public class BeadDAOImpl implements BeadDAO {

	@Override
	public void addBead(Bead bead) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(bead);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bead> listBeads() {

		List<Bead> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Bead.class);
		criteria.addOrder(Order.desc("idBeads"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeBeads(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Bead c = s.load(Bead.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateBeads(Bead beads) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(beads);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bead> listBeadsBySize(String size) {
		Session s = HibernateUtil.openSession();
		List<Bead> result;
		s.beginTransaction();
		result = s.createCriteria(Bead.class).add(Property.forName("size").like(size, MatchMode.ANYWHERE))
				.addOrder(Order.asc("size")).list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Bead> superSearch(String searchString) {
		List<Bead> result;

		String[] searchTerms = searchString.split(" ");
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Bead.class);

		for (String searchTerm : searchTerms) {
			Criterion c = Restrictions.or(Property.forName("size").like(searchTerm, MatchMode.ANYWHERE),
					Property.forName("imageUrl").like(searchTerm, MatchMode.ANYWHERE));
			criteria.add(c);
		}

		result = criteria.list();
		s.close();
		return result;

	}

	@SuppressWarnings("unchecked")
	public List<Bead> findBead(Element element) {
		List<Bead> result;

		String categoryName = element.getElementsByTagName("Category").item(0).getTextContent();
		String subcategoryName = element.getElementsByTagName("Subcategory").item(0).getTextContent();
		NodeList type = element.getElementsByTagName("Type");
		String typeName = "";
		if (type.item(0) != null) {
			typeName = element.getElementsByTagName("Type").item(0).getTextContent();
		}
		String colorName = element.getElementsByTagName("Color").item(0).getTextContent();
		String size = element.getElementsByTagName("Size").item(0).getTextContent();

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query;

		if (type.item(0) != null) {
			query = s.createQuery("FROM Bead as bead "
					+ "inner join fetch bead.category as c "
					+ "left join fetch bead.finishes "
					+ "left join  fetch c.parentCategory as p "
					+ "left join fetch p.parentCategory "
					+ "left join fetch bead.brands "
					+ "left join fetch bead.shapes "
					+ "left join fetch bead.color as col "
					+ " WHERE pp.categoryName=:categoryName "
					+ "AND p.categoryName=:subcategoryName "
					+ "AND c.categoryName=:typeName "
					+ "AND col.colorName=:colorName "
					+ "AND bead.size=:size");

			query.setParameter("typeName", typeName);

		} else {
			query = s.createQuery("FROM Bead as bead "
					+ "inner join fetch bead.category as c "
					+ "left join fetch bead.finishes "
					+ "left join  fetch c.parentCategory as p "
					+ "left join fetch p.parentCategory "
					+ "left join fetch bead.brands "
					+ "left join fetch bead.shapes "
					+ "left join fetch bead.color as col "
					+ "WHERE pp.categoryName=:categoryName "
					+ "AND p.categoryName=:subcategoryName "
					+ "AND col.colorName=:colorName "
					+ "AND bead.size=:size");

		}

		query.setParameter("colorName", colorName);
		query.setParameter("categoryName", categoryName);
		query.setParameter("subcategoryName", subcategoryName);
		query.setParameter("size", size);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		result = query.list();

		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bead> listBeadsForTable() {
		List<Bead> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		String sql = "FROM Bead as bead "
				+ "left join fetch bead.category as c "
				+ "left join fetch bead.finishes "
				+ "left join fetch c.parentCategory as p "
				+ "left join fetch p.parentCategory "
				+ "left join fetch bead.brands "
				+ "left join fetch bead.shapes "
				+ "left join fetch bead.color as col ";

		list = s.createQuery(sql).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bead> listBeadsForTable(String categoryParent, String categoryChild) {
		List<Bead> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Bead.class).createCriteria("category")
				.add(Property.forName("categoryName").like(categoryChild, MatchMode.EXACT))
				.createCriteria("parentCategory")
				.add(Property.forName("categoryName").like(categoryParent, MatchMode.EXACT));

		list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bead> listBeadsForTable(Category category) {
		List<Bead> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM Bead as bead "
				+ "inner join fetch bead.category as c "
				+ "left join fetch bead.finishes "
				+ "left join  fetch c.parentCategory as p "
				+ "left join fetch p.parentCategory "
				+ "left join fetch bead.brands "
				+ "left join fetch bead.shapes "
				+ "left join fetch bead.color as col "
				+ "left join fetch bead.productsInStores as product "
				+ "WHERE c.idCategory=:id "
				+ "ORDER BY col.colorName");
		query.setParameter("id", category.getIdCategory());
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		list = query.list();

		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bead> listBeadsByColor(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		List<Bead> list;
		Query query = s
				.createQuery("FROM Bead as bead "
						+ "inner join fetch bead.category as c "
						+ "left join fetch bead.finishes "
						+ "left join  fetch c.parentCategory as p "
						+ "left join fetch p.parentCategory "
						+ "left join fetch bead.brands "
						+ "left join fetch bead.shapes "
						+ "left join fetch bead.color as col "
						+ "WHERE col.idColor=:id "
				);
		query.setParameter("id", color.getIdColor());
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		list = query.list();

		s.getTransaction().commit();
		s.close();

		return list;
	}

}
