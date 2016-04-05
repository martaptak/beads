package App.Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static void shutdown() {
		// Close caches and connection pools
		sessionFactory.close();
	}
}