package org.hochnt.tutorial.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hochnt.tutorial.hibernate.entities.Department;

public class DepartmentDAO {
	
	/**
	 * Lay mot phong ban theo deptNo
	 * @param session
	 * @param deptNo
	 * @return
	 */
	public static Department findDepartment(Session session, String deptNo) {
		String sql = "Select d from " + Department.class.getName() + " d "//
				+ " Where d.deptNo = :deptNo";
		Query<Department> query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return query.getSingleResult();
	}
}
