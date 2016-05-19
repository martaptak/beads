package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import App.Model.Beads;
import App.Model.Category;
import App.Model.HibernateUtil;
import App.Model.ProductsInStores;

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
		List<ProductsInStores> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("FROM ProductsInStores as p join fetch p.stores").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public void removeProduct(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		ProductsInStores c = (ProductsInStores) s.load(ProductsInStores.class, id);
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
	public List<ProductsInStores> listProducts(Beads bead) {
		List<ProductsInStores> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Query query = s.createQuery("FROM ProductsInStores as s join fetch s.stores where s.beads=:bead");
		query.setParameter("bead", bead);
		list = query.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listOfUnits() {
		List<String> list = new ArrayList<>();
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
