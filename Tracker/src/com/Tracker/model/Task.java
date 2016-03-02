package com.Tracker.model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/*
 * This class represents a single Task object.
 * Hibernate is configured with this class with annotations.
 * Originally, the course I went through taught me the XML configurations,
 * but annotations seemed like the better way to go.
 */

@Entity
@Table(name="tracker_tasks")
public class Task {
	
	private String ID;
	private String title;
	private String description;
	private Date dueDate;
	private TaskStatus status;
	
	/*
	 * Empty constructor for hibernate's use
	 */
	public Task() {	}
	
	/*
	 * This constructor is for ease of initialization
	 */
	public Task(String title, String desc, Date date) {
		this.title = title;
		this.description = desc;
		this.status = TaskStatus.NEW;
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

	public TaskStatus getStatus() {
		return status;
	}

	@Enumerated(EnumType.STRING)
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	/*
	 * I've overriden the toString method so that I can more easily see the relevant 
	 * data for my Tasks when debugging via output to the console.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ID: " + getID() + ", Title: " + getTitle() + ", Description: " + getDescription() + ", Due Date: " + getDueDate();
	}
	
	/*
	 * This method had to be overwritten for the List contains method to work.
	 * For this application, struts2 was implements first and task data was saved
	 * in a List<Task> that was in the session variable. This method was needed in order
	 * to get the index of a Task that was already created.
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object t) {
		return this.ID.equals(((Task)t).getID());
	}
}
