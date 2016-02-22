package com.Tracker.data;

import com.Tracker.data.TaskStatus;
import java.util.Date;

public class Task {
	private String title;
	private String description;
	private Date dueDate;
	private String ID;
	private TaskStatus status;
	
	public Task(String ID, String title, String desc, Date date) {
		this.ID = ID;
		this.title = title;
		this.description = desc;
		setStatus(TaskStatus.NEW);
		this.dueDate = date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() {
		return "ID: " + getID() + ", Title: " + getTitle() + ", Description: " + getDescription() + ", Due Date: " + getDueDate();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	@Override
	public boolean equals(Object t) {
		return this.ID.equals(((Task)t).getID());
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
