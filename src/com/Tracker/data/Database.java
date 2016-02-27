package com.Tracker.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.Tracker.model.Task;

public class Database {
	private List<Task> tasks;
	
	public Database() {
		tasks = new ArrayList<Task>();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void loadTasks() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			tasks = (List<Task>) session.createCriteria(Task.class).list();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	public void addTask(Task t) {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	public void removeTask(String uuid) {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Task t = new Task();
			t.setID(uuid);
			session.delete(t);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
}
