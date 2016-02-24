package com.Tracker.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tasks")
public class Task {
	
	private String ID;
	private String title;
	private String description;
	private Date dueDate;
	private TaskStatus status;
	
	public Task() {
		super();
	}
	
	public Task(String title, String desc, Date date) {
		//this.ID = UUID.randomUUID().toString();
		this.title = title;
		this.description = desc;
		setStatus(TaskStatus.NEW);
		this.dueDate = date;
	}
	
	@Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
	@Column(name="id", unique=true)
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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
	
	@Temporal(TemporalType.DATE)
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
	
	@Override
	public boolean equals(Object t) {
		return this.ID.equals(((Task)t).getID());
	}

	public TaskStatus getStatus() {
		return status;
	}

	@Enumerated(EnumType.STRING)
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
}
