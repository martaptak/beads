package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import App.Model.Country;
import App.Model.HibernateUtil;

public class CountryDAOImpl implements CountryDAO {

	@Override
	public void addCountry(Country country) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(country);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void updateCountry(Country country) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(country);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void removeCountry(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Country c = (Country) s.load(Country.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> countriesList() {

		List<Country> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Country.class);
		criteria.addOrder(Order.asc("nameCountry"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
		
	}

}
