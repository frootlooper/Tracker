package com.Tracker.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import com.Tracker.data.Task;
import com.Tracker.data.TaskStatus;

public class View extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private List<Task> myTasks;
	private String enteredTitle, enteredDescription, enteredDueDate;
	private boolean postBack = false;
	private String dataAction;
	private String taskID;
	
	@Action("view-input")
	public String input() throws Exception {
		if (session.containsKey("myTasks")) {
			this.myTasks = (List<Task>) session.get("myTasks");
		} else {
			this.myTasks = new ArrayList<Task>();
		}
		return SUCCESS;
	}
	
	@Override
	@Action("view")
	public String execute() throws Exception {
		if (session.containsKey("myTasks")) {
			this.myTasks = (List<Task>) session.get("myTasks");
		} else {
			this.myTasks = new ArrayList<Task>();
		}
		if (postBack) {
			if (dataAction.equals("add")) {
				return addTask();
			}
			if (dataAction.equals("delete")) {
				return deleteTask();
			}
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String addTask() {
		Date d;
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(enteredDueDate);
		} catch (Exception e) {
			d = new Date();
		}
		String id = UUID.randomUUID().toString();
		Task t = new Task(id, enteredTitle, enteredDescription, d);
		getMyTasks().add(t);
		session.put("myTasks", getMyTasks());
		this.enteredTitle = "";
		this.enteredDescription = "";
		this.enteredDueDate = "";
		return "ajax";
	}

	public String deleteTask() {
		Task t = new Task(taskID, "", "", new Date());
		int i = myTasks.indexOf(t);
		if (i >=0) {
			this.myTasks.remove(i);
		}
		session.put("myTasks", this.myTasks);
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

//	@RequiredStringValidator(type=ValidatorType.FIELD, message="You must entered a task title.")
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
