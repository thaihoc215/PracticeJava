package util;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	

	private static StandardServiceRegistry registry;
	static {
		if(sessionFactory==null)
		{
			try {
				String dir = System.getProperty("user.dir").toString() + "/src/hibernate.cfg.xml";
				System.out.println(dir);
				File file = new File(dir);
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure(file).build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
			          StandardServiceRegistryBuilder.destroy(registry);
			        }
			}
		}	
	}
	
	/**
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

}
