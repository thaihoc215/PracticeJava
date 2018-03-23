package org.hochnt.tutorial.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hochnt.tutorial.hibernate.entities.Employee;

public class EmployeeDAO {
	
	/**
	 * Lay max empID
	 * @param session
	 * @return
	 */
	public static Long getMaxEmpId(Session session) {
		String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
		Query<Number> query = session.createQuery(sql);
		Number value = query.getSingleResult();
		if (value == null) {
			return 0L;
		}
		return value.longValue();
	}

	/**
	 * Tim kiem mot nhan vien (employee)
	 * @param session
	 * @param empNo
	 * @return
	 */
	public static Employee findEmployee(Session session, String empNo) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " Where e.empNo = :empNo";
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("empNo", empNo);
		return query.getSingleResult();
	}
}
