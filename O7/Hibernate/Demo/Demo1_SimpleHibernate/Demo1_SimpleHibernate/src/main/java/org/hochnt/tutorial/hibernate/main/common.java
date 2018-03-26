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
import org.hochnt.tutorial.hibernate.dao.TimekeeperDAO;
import org.hochnt.tutorial.hibernate.entities.Department;
import org.hochnt.tutorial.hibernate.entities.Employee;
import org.hochnt.tutorial.hibernate.utils.HibernateUtils;

public class common {

	/**
	 * using hql
	 */
	public static void query1() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// Tất cả các lệnh hành động với DB thông qua Hibernate
			// đều phải nằm trong 1 giao dịch (Transaction)
			// Bắt đầu giao dịch
			session.getTransaction().begin();

			// Tạo một câu lệnh HQL query object.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO

			String sql = "Select e from " + Employee.class.getName() + " e " + " order by e.empName, e.empNo ";

			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

	/**
	 * using hql with parameter
	 */
	public static void query2() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// Tất cả các lệnh hành động với DB thông qua Hibernate
			// đều phải nằm trong 1 giao dịch (Transaction)
			// Bắt đầu giao dịch
			session.getTransaction().begin();

			// Tạo một câu lệnh HQL query object.
			// HQL Có tham số.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e cross join DEPARTMENT d
			// where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;

			String sql = "Select e from " + Employee.class.getName() + " e " + " where e.department.deptNo=:deptNo ";

			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);

			query.setParameter("deptNo", "D10");

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

	/**
	 * Thực hiện lấy một số cột sử dụng object
	 */
	public static void querySomeColumns() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Query một vài cột.
			// Việc lấy dữ liệu trong trường hợp này sẽ phức tạp hơn.

			String sql = "Select e.empId, e.empNo, e.empName from " + Employee.class.getName() + " e ";

			Query<Object[]> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng Object[]

			List<Object[]> datas = query.getResultList();

			for (Object[] emp : datas) {
				System.out.println("Emp Id: " + emp[0]);
				System.out.println(" Emp No: " + emp[1]);
				System.out.println(" Emp Name: " + emp[2]);
			}

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

	/**
	 * Thực hiện lấy một số cột sử dụng Beans
	 */
	public static void ShortEmpInfoQueryDemo() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Sử dụng cấu tử của Class ShortEmpInfo
			String sql = "Select new " + ShortEmpInfo.class.getName() + "(e.empId, e.empNo, e.empName)" + " from "
					+ Employee.class.getName() + " e ";

			Query<ShortEmpInfo> query = session.createQuery(sql);

			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng ShortEmpInfo
			List<ShortEmpInfo> employees = query.getResultList();

			for (ShortEmpInfo emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			// Commit data.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

	/**
	 * Query lấy dữ liệu duy nhất
	 */
	public static void UniqueResultDemo() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			// Lay bo phan
			Department dept = getDepartment(session, "D10");
			Set<Employee> emps = dept.getEmployees();
			// Lay danh sach nhan vien theo bo phan tren
			System.out.println("Dept Name: " + dept.getDeptName());
			for (Employee emp : emps) {
				System.out.println(" Emp name: " + emp.getEmpName());
			}

			// Lay nhan vien theo ma nhan vien
			Employee emp = getEmployee(session, 7839L);
			System.out.println("Emp Name: " + emp.getEmpName());

			// Commit data.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
	}

	public static Department getDepartment(Session session, String deptNo) {
		String sql = "Select d from " + Department.class.getName() + " d "//
				+ " where d.deptNo= :deptNo ";
		Query<Department> query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return (Department) query.getSingleResult();
	}

	public static Employee getEmployee(Session session, Long empId) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " where e.empId= :empId ";
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("empId", empId);
		return (Employee) query.getSingleResult();
	}

	/**
	 * Đối tượng trong hibernate
	 * 
	 * @param session
	 * @param emp
	 * @param department
	 */
	public static void hibernateObject(Session session, Department department, Employee emp) {
		session.getTransaction().begin();

		Long maxEmpId = EmployeeDAO.getMaxEmpId(session);
		Long empId = maxEmpId + 1;

		// Phòng ban với mã số D10.
		// Nó là đối tượng chịu sự quản lý của session
		// Và được gọi là đối tượng persistent.

		department = DepartmentDAO.findDepartment(session, "D10");

		// Tạo mới đối tượng Employee
		// Đối tượng này chưa chịu sự quản lý của session.
		// Nó được coi là đối tượng Transient.

		emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpNo("E" + empId);
		emp.setEmpName("Name " + empId);
		emp.setJob("Coder");
		emp.setSalary(1000f);
		emp.setManager(null);
		emp.setHideDate(new Date());
		emp.setDepartment(department);

		// Sử dụng persist(..)
		// Lúc này 'emp' đã chịu sự quản lý của session.
		// nó có trạng thái persistent.
		// Chưa có hành động gì với DB tại đây.

		session.persist(emp);

		// Tại bước này dữ liệu mới được đẩy xuống DB.
		// Câu lệnh Insert được tạo ra.

		session.getTransaction().commit();

	}

	// Demo đối tượng persistent. Thay đổi dữ liệu trên một dữ liệu đã có sẵn khi
	// đối tượng nằm trong persistent
	public static void hibenatePersistence(Session session, Department department) {
		session.getTransaction().begin();

		System.out.println("- Finding Department deptNo = D10...");
		// Đây là một đối tượng có trạng thái Persistent.
		department = DepartmentDAO.findDepartment(session, "D10");
		System.out.println("- Department deptNo = D10: " + department.getLocation());

		System.out.println("- First change Location");
		// Thay đổi gì đó trên đối tượng Persistent.
		department.setLocation("Chicago " + System.currentTimeMillis());
		System.out.println("- Location = " + department.getLocation());
		System.out.println("- Calling flush...");
		// Sử dụng session.flush() để chủ động đẩy các thay đổi xuống DB.
		// Có tác dụng trên tất cả các đối tượng Persistent có thay đổi.
		session.flush();
		System.out.println("- Flush OK");

		System.out.println("- Second change Location");
		// Thay đổi gì đó trên đối tượng Persistent.
		// Hình thành câu lệnh update, sẽ được thực thi
		// sau khi session đóng lại (commit).
		department.setLocation("Chicago2 " + System.currentTimeMillis());
		// In ra Location.
		System.out.println("- Location = " + department.getLocation());

		System.out.println("- Calling commit...");
		// Lệnh commit sẽ làm tất cả những sự thay đổi được đẩy xuống DB.
		session.getTransaction().commit();
		System.out.println("- Commit OK");

	}

	/**
	 * Sử dụng transient to persistent
	 * 
	 * @param session
	 * @param emp
	 */

	public static void transistToPersist(Session session, Employee emp) {
		session.getTransaction().begin();

		emp = EmployeeDAO.findEmployee(session, "E7499");

		// sử dụng persist
		// TimekeeperDAO.persist_Transient(session, emp);

		// sử dụng save()
		TimekeeperDAO.persist_Transient_Save(session, emp);
		session.getTransaction().commit();
	}
}
