package entities;
// Generated Mar 16, 2018 11:21:56 AM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEE", schema = "dbo", catalog = "staffmanager", uniqueConstraints = @UniqueConstraint(columnNames = "EMP_NO"))
public class Employee implements java.io.Serializable {

	private long empId;
	private Department department;
	private Employee employeeByMngId;
//	private Employee employeeByEmpId;
	private String empName;
	private String empNo;
	private Date hireDate;
	private byte[] image;
	private String job;
	private double salary;
	private Set<Employee> employeesForMngId = new HashSet<Employee>(0);
	private Employee employeeByEmpId;
	private Set<Timekeeper> timekeepers = new HashSet<Timekeeper>(0);

	public Employee() {
	}

	public Employee(Department department, Employee employeeByEmpId, String empName, String empNo, Date hireDate,
			String job, double salary) {
		this.department = department;
		this.employeeByEmpId = employeeByEmpId;
		this.empName = empName;
		this.empNo = empNo;
		this.hireDate = hireDate;
		this.job = job;
		this.salary = salary;
	}

	public Employee(Department department, Employee employeeByMngId, Employee employeeByEmpId, String empName,
			String empNo, Date hireDate, byte[] image, String job, double salary, Set<Employee> employeesForMngId,
			Set<Timekeeper> timekeepers) {
		this.department = department;
		this.employeeByMngId = employeeByMngId;
		this.employeeByEmpId = employeeByEmpId;
		this.empName = empName;
		this.empNo = empNo;
		this.hireDate = hireDate;
		this.image = image;
		this.job = job;
		this.salary = salary;
		this.employeesForMngId = employeesForMngId;
//		this.employeeByEmpId = employeeByEmpId;
		this.timekeepers = timekeepers;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "employeeByEmpId"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "EMP_ID", unique = true, nullable = false)
	public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = false)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MNG_ID")
	public Employee getEmployeeByMngId() {
		return this.employeeByMngId;
	}

	public void setEmployeeByMngId(Employee employeeByMngId) {
		this.employeeByMngId = employeeByMngId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Employee getEmployeeByEmpId() {
		return this.employeeByEmpId;
	}

	public void setEmployeeByEmpId(Employee employeeByEmpId) {
		this.employeeByEmpId = employeeByEmpId;
	}

	@Column(name = "EMP_NAME", nullable = false, length = 50)
	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "EMP_NO", unique = true, nullable = false, length = 20)
	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE", nullable = false, length = 10)
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Column(name = "IMAGE")
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "JOB", nullable = false, length = 30)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "SALARY", nullable = false, precision = 53, scale = 0)
	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeByMngId")
	public Set<Employee> getEmployeesForMngId() {
		return this.employeesForMngId;
	}

	public void setEmployeesForMngId(Set<Employee> employeesForMngId) {
		this.employeesForMngId = employeesForMngId;
	}

//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employeeByEmpId")
//	public Employee getEmployeeByEmpId() {
//		return this.employeeByEmpId;
//	}
//
//	public void setEmployeeByEmpId(Employee employeeByEmpId) {
//		this.employeeByEmpId = employeeByEmpId;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Timekeeper> getTimekeepers() {
		return this.timekeepers;
	}

	public void setTimekeepers(Set<Timekeeper> timekeepers) {
		this.timekeepers = timekeepers;
	}

}
