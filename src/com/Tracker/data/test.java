package com.Tracker.data;

import java.util.Date;
import java.util.UUID;
import org.hibernate.Session;

public class test {

	public static void main(String[] args) {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		Task t = new Task(UUID.randomUUID().toString(), "Dishes", "All of them", new Date());
		t.setStatus(TaskStatus.INPROGRESS);
		session.save(t);
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		session.getTransaction().commit();
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}
}
