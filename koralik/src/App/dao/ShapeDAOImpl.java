package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import App.Model.HibernateUtil;
import App.Model.Shape;

public class ShapeDAOImpl implements ShapeDAO{

	@Override
	public void addShape(Shape shape) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(shape);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public void updateShape(Shape shape) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(shape);
		s.getTransaction().commit();
		s.close();
		
		
	}

	@Override
	public void removeShape(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Shape c = (Shape) s.load(Shape.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shape> shapesList() {
		List<Shape> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Criteria criteria = s.createCriteria(Shape.class);
		criteria.addOrder(Order.asc("shapeName"));
		list = criteria.list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

}
