package org.hochnt.tutorial.hibernate.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hochnt.tutorial.hibernate.entities.Employee;
import org.hochnt.tutorial.hibernate.entities.Timekeeper;

public class TimekeeperDAO {
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Thêm một Timekeeper sử dụng persist
	 * 
	 * @param session
	 * @param emp
	 * @return
	 */
	public static Timekeeper persist_Transient(Session session, Employee emp) {
		// Hãy chú ý:
		// timekeeperId cấu hình tự động được tạo ra bởi UUID.
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		// Tạo một đối tượng, nó đang có tình trạng Transient.

		Timekeeper tk1 = new Timekeeper();
		tk1.setEmployee(emp);
		tk1.setInOut(Timekeeper.IN);
		tk1.setDateTime(new Date());

		// Now, 'tk1' is transient object
		System.out.println("- tk1 Persistent? " + session.contains(tk1));

		System.out.println("====== CALL persist(tk).... ===========");
		// Hibernate gán Id vào 'tk1', sẽ chưa có insert gì cả
		session.persist(tk1);
		// 'tk1' đã được gắn ID
		System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());

		// Lúc này 'tk1' đã có trạng thái Persistent
		// Nó đã được quản lý trong Session.
		// Nhưng chưa có hành động gì insert xuống DB.
		// ==> true
		System.out.println("- tk1 Persistent? " + session.contains(tk1));
		System.out.println("- Call flush..");
		// Chủ động đẩy dữ liệu xuống DB, gọi flush().
		// Nếu không gọi flush() dữ liệu sẽ được đẩy xuống tại lệnh commit().
		// Lúc này mới có insert.
		session.flush();

		String timekeeperId = tk1.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk1.getInOut());
		System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
		System.out.println();
		return tk1;
	}

	/**
	 * Transient --> Persistent : Sử dụng save(Object) *
	 * 
	 * @param session
	 * @param emp
	 * @return
	 */
	public static Timekeeper persist_Transient_Save(Session session, Employee emp) {
		// Hãy chú ý:
		// timekeeperId cấu hình tự động được tạo ra bởi UUID.
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		// Tạo một đối tượng, nó đang có tình trạng Transient.
		Timekeeper tk2 = new Timekeeper();
		tk2.setEmployee(emp);
		tk2.setInOut(Timekeeper.IN);
		tk2.setDateTime(new Date());

		// Lúc này 'tk2' đang có tình trạng Transient.
		System.out.println("- tk2 Persistent? " + session.contains(tk2));

		System.out.println("====== CALL save(tk).... ===========");
		// save() rất giống với persist()
		// save() trả về ID còn persist() là void.
		// Hibernate gán Id vào 'tk2', sẽ chưa có insert gì cả
		// Nó trả về ID của 'tk2'.
		Serializable id = session.save(tk2);
		System.out.println("- id = " + id);

		// 'tk2' đã được gắn ID
		System.out.println("- tk2.getTimekeeperId() = " + tk2.getTimekeeperId());
		// Lúc này 'tk2' đã có trạng thái Persistent
		// Nó đã được quản lý trong Session.
		// Nhưng chưa có hành động gì insert xuống DB.
		// ==> true
		System.out.println("- tk2 Persistent? " + session.contains(tk2));
		System.out.println("- Call flush..");
		// Chủ động đẩy dữ liệu xuống DB, gọi flush().
		// Nếu không gọi flush() dữ liệu sẽ được đẩy xuống tại lệnh commit().
		// Lúc này mới có insert.
		session.flush();

		String timekeeperId = tk2.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk2.getInOut());
		System.out.println("- dateTime = " + df.format(tk2.getDateTime()));
		System.out.println();
		return tk2;
	}
}
