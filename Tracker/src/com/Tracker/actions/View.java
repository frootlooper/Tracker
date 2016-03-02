package com.Tracker.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.Tracker.data.Database;
import com.Tracker.model.Task;
import com.Tracker.model.TaskStatus;

/*
 * This is the view action for the web app.
 * Returns success on initial load and ajax for every other operation.
 * Ajax will refresh the result div with the view-ajax.ftl contents.
 */
public class View extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Task> myTasks;
	private String enteredTitle, enteredDescription, enteredDueDate, dataAction, taskID;
	private boolean postBack = false;
	private TaskStatus enteredStatus;
	private static Database db = new Database();
	
	/*
	 * This method is the action called by the forms on the webpage.
	 * It handles initial load and redirects to the desired method.
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Action("view")
	public String execute() throws Exception {
		if (postBack) { //A form has been submitted, so check which one.
			if (dataAction.equals("add")) {
				return addTask();
			}
			if (dataAction.equals("delete")) {
				return deleteTask();
			}
			if (dataAction.equals("edit")) {
				return editTask();
			}
		} else {
			// A form has not been submitted so it must be the initial page load.
			this.myTasks = db.getTasks();
		}
		return SUCCESS;
	}
	
	/*
	 * Take entered fields and create a new Task object, then add it to the database.
	 * Reload the current tasks.
	 * Return "ajax" so that page is not refreshed.
	 */
	public String addTask() {
		Date d;
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(enteredDueDate);
		} catch (Exception e) {
			d = new Date();
		}
		Task t = new Task(enteredTitle, enteredDescription, d);
		db.addTask(t);
		this.myTasks = db.getTasks();
		this.enteredTitle = "";
		this.enteredDescription = "";
		this.enteredDueDate = "";
		return "ajax";
	}

	/*
	 * Give the task ID to the database for deletion.
	 * Reload the current tasks.
	 * Return "ajax" so that the page isn't refreshed.
	 */
	public String deleteTask() {
		db.removeTask(taskID);
		this.myTasks = db.getTasks();
		
		return "ajax";
	}
	
	/*
	 * Create a new Task object based on field values.
	 * Send this task to the database for update.
	 * Reload the current tasks.
	 * Return "ajax" so that the page isn't refreshed.
	 */
	public String editTask() {
		Date d;
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(enteredDueDate);
		} catch (Exception e) {
			d = new Date();
		}
		Task t = new Task(enteredTitle, enteredDescription, d);
		t.setID(taskID);
		t.setStatus(getEnteredStatus());
		System.out.println(getEnteredStatus());
		db.editTask(t);
		this.myTasks = db.getTasks();
		return "ajax";
	}
	
	/*
	 * Returns the list of options for the edit dialog.
	 * Need to rewrite so that it pulls from the ENUM class isntead of hardcoded.
	 */
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

	public TaskStatus getEnteredStatus() {
		return enteredStatus;
	}

	public void setEnteredStatus(TaskStatus enteredStatus) {
		this.enteredStatus = enteredStatus;
	}
}
