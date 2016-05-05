package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<ColorFamily> listColorFamily() {
		Session s = HibernateUtil.openSession();
		List<ColorFamily> list = new ArrayList<ColorFamily>();
		s.beginTransaction();
		list = s.createQuery("FROM ColorFamily").list();

		s.getTransaction().commit();
		s.close();

		return list;
	}

}
