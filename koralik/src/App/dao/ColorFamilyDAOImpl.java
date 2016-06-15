package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import App.Model.ColorFamily;
import App.Model.HibernateUtil;

public class ColorFamilyDAOImpl implements ColorFamilyDAO {

	@Override
	public void updateColorFamily(ColorFamily colorFamily) {

		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(colorFamily);
		s.getTransaction().commit();
		s.close();

	}
	
	@Override
	public void addColorFamily(ColorFamily colorFamily){
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(colorFamily);
		s.getTransaction().commit();
		s.close();
	}
	
	@Override
	public void removeColorFamily(ColorFamily colorFamily){
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.delete(colorFamily);
		s.getTransaction().commit();
		s.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ColorFamily> listColorFamily() {
		Session s = HibernateUtil.openSession();
		List<ColorFamily> list = new ArrayList<ColorFamily>();
		s.beginTransaction();
		//list = s.createQuery("FROM ColorFamily").list();
		
		Criteria criteria = s.createCriteria(ColorFamily.class);
		criteria.addOrder(Order.asc("colorFamilyName"));
		list = criteria.list();

		s.getTransaction().commit();
		s.close();

		return list;
	}

	@Override
	public ColorFamily getDefaultColorFamily() {

		Session s = HibernateUtil.openSession();

		s.beginTransaction();
		ColorFamily colorFamily = (ColorFamily) s.load(ColorFamily.class, 11);
		s.getTransaction().commit();
		s.close();

		return colorFamily;
	}

}
