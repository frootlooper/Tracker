package com.Tracker.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.Tracker.model.Task;

public class Database {
	private List<Task> tasks;
	private Session session;
	
	public Database() {
		tasks = new ArrayList<Task>();
		session = HibernateUtilities.getSessionFactory().openSession();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void loadTasks() {
		session.beginTransaction();
		tasks = (List<Task>) session.createCriteria(Task.class).list();
		session.getTransaction().commit();
	}
	
	public void addTask(Task t) {
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
	}
	
	public void removeTask(String uuid) {
		System.out.println("In remove Task method");
		session.beginTransaction();
		Task t = new Task("","",new Date());
		t.setID(uuid);
		session.delete(t);
		session.getTransaction().commit();
	}
	
	public void close() {
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}
}
