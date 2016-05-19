package App.dao;

import java.util.ArrayList;
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

import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
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
		s.close();
		return result;

	}

	@SuppressWarnings("unchecked")
	public List<Beads> findBead(Element element) {
		List<Beads> result = new ArrayList<Beads>();

		String categoryName = element.getElementsByTagName("Category").item(0).getTextContent();
		String subcategoryName = element.getElementsByTagName("Subcategory").item(0).getTextContent();
		NodeList type = element.getElementsByTagName("Type");
		String typeName = "";
		if (type.item(0) != null){
			typeName = element.getElementsByTagName("Type").item(0).getTextContent();
		}				
		String colorName = element.getElementsByTagName("Color").item(0).getTextContent();
		String size = element.getElementsByTagName("Size").item(0).getTextContent();		

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = null;
		
		if(type.item(0) != null){
		
		query= s.createQuery("FROM Beads as bead inner join fetch bead.category as c "
					+ " left join fetch bead.finishes " + "join fetch c.parentCategory as p "
					+ "join fetch p.parentCategory as pp " + "left join fetch bead.color as col "
					+ " WHERE pp.categoryName=:categoryName " + "AND p.categoryName=:subcategoryName "
					+ "AND c.categoryName=:typeName " + "AND col.colorName=:colorName " + "AND bead.size=:size");
			
		
		query.setParameter("typeName", typeName);		
		
		}
		
		else{
			query= s.createQuery("FROM Beads as bead inner join fetch bead.category as c "
					+ " left join fetch bead.finishes " + "join fetch c.parentCategory as p "
					+ "join fetch p.parentCategory as pp " + "left join fetch bead.color as col "
					+ " WHERE pp.categoryName=:categoryName " + "AND p.categoryName=:subcategoryName "
					+ "AND col.colorName=:colorName " + "AND bead.size=:size");
			
		}
		
		query.setParameter("colorName", colorName);		
		query.setParameter("categoryName", categoryName);
		query.setParameter("subcategoryName", subcategoryName);
		query.setParameter("size", size);
		result = query.list();
		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsForTable() {
		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		String sql = "from Beads as bead " + "inner join fetch bead.category as c " + "join fetch c.parentCategory "
				+ "inner join fetch bead.color " + "left join fetch bead.finishes";

		list = s.createQuery(sql).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsForTable(String categoryParent, String categoryChild) {
		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Beads.class).createCriteria("category")
				.add(Property.forName("categoryName").like(categoryChild, MatchMode.EXACT))
				.createCriteria("parentCategory")
				.add(Property.forName("categoryName").like(categoryParent, MatchMode.EXACT));

		list = criteria.list();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsForTable(Category category) {
		List<Beads> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM Beads as bead inner join fetch bead.category as c "
				+ " left join fetch bead.finishes "
				+ "join fetch c.parentCategory as p inner join fetch bead.color as col  WHERE c.idCategory=:id");
		query.setParameter("id", category.getIdCategory());
		list = query.list();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Beads> listBeadsByColor(Color color) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		List<Beads> list = new ArrayList<>();
		Query query = s.createQuery(
				"FROM Beads as bead inner join fetch bead.category as c " + "left join fetch bead.finishes "
						+ "join fetch c.parentCategory inner join fetch bead.color as col WHERE col.idColor=:id");
		query.setParameter("id", color.getIdColor());
		list = query.list();
		s.getTransaction().commit();
		s.close();

		return list;
	}

}
