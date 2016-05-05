package App.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import App.Model.HibernateUtil;
import App.Model.Stores;

public class StoresDAOImpl implements StoresDAO {

	
	public void addStore(Stores store){
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.save(store);
		s.getTransaction().commit();
		s.close();
	}
	@SuppressWarnings("unchecked")
	public List<Stores> listStores(){
		List<Stores> list = new ArrayList<>();
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		list = s.createQuery("FROM Stores").list();
		s.getTransaction().commit();
		s.close();
		return list;
	}
	public void removeStore(Integer id) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		Stores c = (Stores) s.load(Stores.class, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
	public void updateStore(Stores store) {
		Session s = HibernateUtil.openSession();
		s.beginTransaction();
		s.update(store);
		s.getTransaction().commit();
		s.close();
	}
}
