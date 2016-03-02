package com.Tracker.data;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.Tracker.model.Task;

/*
 * Class manages all the operations needed to be performed on the data including create, delete and edit.
 */
public class Database {
	private SessionFactory factory;
	
	public Database() {
		factory = HibernateUtilities.getSessionFactory();
	}
	
	/*
	 * Loads all of the tasks in the database into a List<Task>
	 * and returns it.
	 */
	public List<Task> getTasks() {
		List<Task> tasks = null;
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			tasks = (List<Task>) session.createCriteria(Task.class)
					.addOrder(Order.asc("status"))
					.addOrder(Order.asc("dueDate"))
					.list();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return tasks;
	}
	
	/*
	 * Adds a new task to the database.
	 */
	public void addTask(Task t) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	/*
	 * Removes a task based on its uuid from the database.
	 */
	public void removeTask(String uuid) {
		Session session = factory.openSession();
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
	
	/*
	 * Edits a specific task with the properties of the task passed in.
	 */
	public void editTask(Task t) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
}
