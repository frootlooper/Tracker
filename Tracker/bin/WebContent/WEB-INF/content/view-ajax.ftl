
	<table class="table table-bordered">
		<caption>Task List</caption>
		<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Due Date</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<@s.iterator value="myTasks" status="status">
			<tr>
				<th><@s.property value="title"/></th>
				<th><@s.property value="description"/></th>
				<th><@s.property value="%{dueDate}"/></th>
				<th><@s.property value="status"/></th>
				<th>
					<@s.form id="deleteTaskForm%{#status.count}" action="view" cssClass="form-inline">
						<@s.hidden name="dataAction" value="delete" />
						<@s.hidden name="taskID" value="%{ID}" />
						<@s.hidden name="postBack" value="true" />
						<@sj.submit value="Delete" formIds="deleteTaskForm%{#status.count}" targets="result" button="true" cssClass="btn btn-default" />
					</@s.form>
					
					<@sj.a button="true" onClick="$('#editTaskFormDialog%{#status.count}').dialog('open');" class="btn btn-default">Edit</@sj.a>
					
					<@sj.dialog id="editTaskFormDialog%{#status.count}" autoOpen="false" modal="true" title="Edit Task" draggable="false">
						<@s.form id="editTaskForm%{#status.count}" action="view" cssClass="form-group">
							<@s.hidden name="dataAction" value="edit" />
							<@s.hidden name="taskID" value="%{ID}" />
							<@s.hidden name="postBack" value="true" />
							<@s.textfield name="enteredTitle" value="%{title}" label="Task Title" labelposition="top" cssClass="form-control form-group" />
							<@s.textarea name="enteredDescription" value="%{description}" label="Task Description" labelposition="top" cssClass="form-control form-group" />
							<@sj.datepicker name="enteredDueDate" value="%{dueDate}" label="Due Date" labelposition="top" changeYear="true" cssClass="datepicker form-control form-group" />
							<@s.select name="enteredStatus" list="statuses" value="%{status}" label="Status" labelposition="top" cssClass="form-control form-group"/>
							<@sj.submit value="Submit" onClick="$('#editTaskFormDialog%{#status.count}').dialog('close');" formIds="editTaskForm%{#status.count}" targets="result" button="true" cssClass="btn btn-default" />
						</@s.form>
					</@sj.dialog>
				</th>
			</tr>
		</@s.iterator>
		</tbody>
	</table>
	<@s.if test="%{myTasks.isEmpty()}">
		<div>You have no tasks.</div>
	</@s.if>