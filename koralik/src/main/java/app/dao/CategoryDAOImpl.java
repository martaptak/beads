package app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import app.model.Category;
import app.model.HibernateUtil;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public void create(String name) {

		Category newCategory = new Category(name);

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(newCategory);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void create(String name, Category parent) {

		Category newCategory = new Category(name, parent);

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(newCategory);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void update(Category category) {

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(category);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void removeCategory(Category c) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		s.delete(c);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public Category getByName(String name) {

		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		Query query = s.createQuery("FROM Category WHERE categoryName=:name");
		query.setParameter("name", name);
		Category result = (Category) query.uniqueResult();
		s.getTransaction().commit();
		s.close();

		return result;

	}

	@Override
	public Category getById(int id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		Query query = s.createQuery("FROM Category WHERE idCategory=:id");
		query.setParameter("id", id);
		Category result = (Category) query.uniqueResult();
		s.getTransaction().commit();
		s.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listMainParents() {

		List<Category> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		Criteria criteria = s.createCriteria(Category.class, "category");
		criteria.add(Restrictions.eq("category.depth", 0));
		criteria.addOrder(Order.asc("category.categoryName"));
		list = criteria.list();

		// list = s.createQuery("FROM Category K WHERE K.depth = 0").list();
		s.getTransaction().commit();
		s.close();
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> listChildren(Category parent) {
		List<Category> list;
		Session s = HibernateUtil.openSession();
		s.beginTransaction();

		Criteria criteria = s.createCriteria(Category.class, "category");
		criteria.add(Restrictions.eq("category.parentCategory", parent));
		criteria.addOrder(Order.asc("category.categoryName"));
		list = criteria.list();

		// list = s.createQuery("FROM Category K WHERE
		// parentCategory=:parent").setParameter("parent", parent).list();
		s.getTransaction().commit();
		s.close();
		return list;

	}

	@Override
	public void add(Category category) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(category);
		s.getTransaction().commit();
		s.close();

	}


}