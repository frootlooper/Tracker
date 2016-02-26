package com.Tracker.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.Tracker.data.Database;
import com.Tracker.model.Task;

public class View extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Task> myTasks;
	private String enteredTitle, enteredDescription, enteredDueDate;
	private boolean postBack = false;
	private String dataAction;
	private String taskID;
	private Database db;
	
	@Action("view-input")
	public String input() throws Exception {
		if (db == null) {
			db = new Database();
		}
		db.loadTasks();
		this.myTasks=db.getTasks();
		return SUCCESS;
	}
	
	@Override
	@Action("view")
	public String execute() throws Exception {
		if (db == null) {
			db = new Database();
		}
		if (postBack) {
			if (dataAction.equals("add")) {
				return addTask();
			}
			if (dataAction.equals("delete")) {
				return deleteTask();
			}
		} else {
			db.loadTasks();
			this.myTasks = db.getTasks();
		}
		return SUCCESS;
	}
	
	public String addTask() {
		Date d;
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(enteredDueDate);
		} catch (Exception e) {
			d = new Date();
		}
		Task t = new Task(enteredTitle, enteredDescription, d);
		db.addTask(t);
		db.loadTasks();
		this.myTasks = db.getTasks();
		this.enteredTitle = "";
		this.enteredDescription = "";
		this.enteredDueDate = "";
		return "ajax";
	}

	public String deleteTask() {
		if (db == null) {
			db = new Database();
		}
		db.removeTask(taskID);
		db.loadTasks();
		this.myTasks = db.getTasks();
		
		return "ajax";
	}
	
	public String[] getStatuses() {
		return new String[]{"NEW", "INPROGRESS", "DONE"};
	}
	
	public String getEnteredDescription() {
		return enteredDescription;
	}

	public void setEnteredDescription(String enteredDescription) {
		this.enteredDescription = enteredDescription;
	}

	public String getEnteredTitle() {
		return enteredTitle;
	}

	public void setEnteredTitle(String enteredTitle) {
		this.enteredTitle = enteredTitle;
	}

	public List<Task> getMyTasks() {
		return myTasks;
	}

	public void setMyTasks(List<Task> myTasks) {
		this.myTasks = myTasks;
	}

	public boolean isPostBack() {
		return postBack;
	}

	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}

	public String getDataAction() {
		return dataAction;
	}

	public void setDataAction(String dataAction) {
		this.dataAction = dataAction;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getEnteredDueDate() {
		return enteredDueDate;
	}

	public void setEnteredDueDate(String enteredDueDate) {
		this.enteredDueDate = enteredDueDate;
	}
}
