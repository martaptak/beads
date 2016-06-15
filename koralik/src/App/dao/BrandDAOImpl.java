package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import App.Model.Brand;
import App.Model.HibernateUtil;

public class BrandDAOImpl implements BrandDAO{

	@Override
	public void addBrand(Brand brand) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(brand);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void updateBrand(Brand brand) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(brand);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void removeBrand(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Brand c = (Brand) s.load(Brand.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> brandsList() {
		List<Brand> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Brand.class);
		criteria.addOrder(Order.asc("brandName"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

}
