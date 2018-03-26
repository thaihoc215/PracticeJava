package org.hochnt.tutorial.hibernate.main;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hochnt.tutorial.hibernate.beans.ShortEmpInfo;
import org.hochnt.tutorial.hibernate.dao.DepartmentDAO;
import org.hochnt.tutorial.hibernate.dao.EmployeeDAO;
import org.hochnt.tutorial.hibernate.entities.Department;
import org.hochnt.tutorial.hibernate.entities.Employee;
import org.hochnt.tutorial.hibernate.utils.HibernateUtils;

public class main {

	public static void main(String[] args) {
		// query1();
		// query2();
		// querySomeColumns();
		// ShortEmpInfoQueryDemo();
		// UniqueResultDemo();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		Department department = null;
		Employee emp = null;
		try {
			// đối tượng trong hibernate
			// common.hibernateObject(session,department,emp);

			// đối tượng persistence
//			common.hibenatePersistence(session,department);
			
			//Transient --> Persistent : Sử dụng persist(Object)
			common.transistToPersist(session,emp);
			

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// Sau khi session bị đóng lại (commit, rollback, close)
		// Đối tượng 'emp', 'dept' trở thành đối tượng Detached.
		// Nó không còn trong sự quản lý của session nữa.

//		System.out.println("Emp No: " + emp.getEmpNo());
	}

}
