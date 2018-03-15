package Utils;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// Hibernate 5:
	private static SessionFactory buildSessionFactory() {
		try {
			// Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml	
			String dir = System.getProperty("user.dir").toString() + "/hibernate.cfg.xml";
			System.out.println(dir);
			File file = new File(dir);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.configure(file).build();
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.configure("hibernate.cfg.xml").build();
			

			// Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Kết nối Database không thành công","Lỗi", JOptionPane.ERROR_MESSAGE);
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
			
			
		}
	}

	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}

	public static void shutdown() {
		// Giải phóng cache và Connection Pools.
		getSessionFactory().close();
	}
}
