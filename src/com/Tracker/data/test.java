package com.Tracker.data;

import java.util.Date;
import org.hibernate.Session;
import com.Tracker.model.Task;
import com.Tracker.model.TaskStatus;

public class test {

	public static void main(String[] args) {
		Database db = new Database();
		db.loadTasks();
		
		Task t = new Task("title", "description", new Date());
		db.addTask(t);
		System.out.println(db.getTasks());
		db.close();
	}
}
