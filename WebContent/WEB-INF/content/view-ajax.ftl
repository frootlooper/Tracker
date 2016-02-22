	<div id=addNewForm>
		<h4>Add New Task</h4>
		<@s.form id="createTaskForm" action="view" validate="true" cssClass="form-group">
			<@s.hidden name="postBack" value="true" />
			<@s.hidden name="dataAction" value="add" />
			<@s.textfield name="enteredTitle" label="Task Title" labelposition="top" cssClass="form-control form-group" />
			<@s.textarea name="enteredDescription" label="Task Description" labelposition="top" cssClass="form-control form-group" />
			<@sj.datepicker name="enteredDueDate" label="Due Date" labelposition="top" changeYear="true" cssClass="datepicker form-control form-group" />
			<@sj.submit value="Add" formIds="createTaskForm" targets="result" button="true" cssClass="btn btn-default" />
		</@s.form>
	</div>
			
	
	<table class="table table-bordered">
		<caption>Task List</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Due Date</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<@s.if test="%{myTasks.isEmpty()}">
			<div>You have no tasks.</div>
		</@s.if>
		<@s.iterator value="myTasks" status="status">
			<tr>
				<th scope="row"><@s.property value="ID"/></th>
				<th><@s.property value="title"/></th>
				<th><@s.property value="description"/></th>
				<th><@s.property value="%{dueDate}"/></th>
				<th><@s.property value="status"/></th>
				<th>
					<@s.form id="deleteTaskForm%{#status.count}" action="view">
						<@s.hidden name="dataAction" value="delete" />
						<@s.hidden name="taskID" value="%{ID}" />
						<@s.hidden name="postBack" value="true" />
						<@sj.submit value="Delete" formIds="deleteTaskForm%{#status.count}" targets="result" button="true" cssClass="btn btn-default" />
					</@s.form>
				</th>
			</tr>
		</@s.iterator>
		</tbody>
	</table>