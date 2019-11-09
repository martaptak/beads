package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import app.model.Bead;
import app.model.HibernateUtil;
import app.model.ProductsInStores;

public class ProductInStoresDAOImpl implements ProductInStoresDAO {

	@Override
	public void addProduct(ProductsInStores product) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(product);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsInStores> listProducts() {
		List<ProductsInStores> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		//	list = s.createQuery("FROM ProductsInStores as p join fetch p.stores").list();
		Criteria criteria = s.createCriteria(ProductsInStores.class, "product");
		criteria.createAlias("product.stores", "stores");
		list = criteria.list();

		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeProduct(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		ProductsInStores c = s.load(ProductsInStores.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void updateProduct(ProductsInStores product) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(product);
		s.getTransaction().commit();
		s.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsInStores> listProducts(Bead bead) {
		List<ProductsInStores> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		/*Query query = s.createQuery("FROM ProductsInStores as s join fetch s.stores where s.beads=:bead");
		query.setParameter("bead", bead);
		list = query.list();*/

		Criteria criteria = s.createCriteria(ProductsInStores.class, "product");
		criteria.createAlias("product.stores", "stores");
		criteria.add(Restrictions.eq("product.beads", bead));
		list = criteria.list();

		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listOfUnits() {
		List<String> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(ProductsInStores.class);
		criteria.setProjection(Projections.distinct(Projections.property("unit")));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

}
