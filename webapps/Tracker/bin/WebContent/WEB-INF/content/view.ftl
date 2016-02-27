<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" />
<!-- <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script> -->
<!-- <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->

<@sj.head/>

<script type="text/javascript">
$.subscribe('closedialog',function(event,data){
    $("#addNewTaskForm").dialog('close');
});

$.subscribe('opendialog',function(event,data){
	$(".clear").each( function() {
			$(this).val("");
	});
    $("#addNewTaskForm").dialog('open');
});
</script>

<style>
#createTaskForm label {
	color: black;
}
.ui-datepicker-trigger {
	display: none;
}
</style>

<title>View Tasks</title>

</head>
<body>
 	<@sj.a button="true" onClickTopics="opendialog" class="btn btn-default">Add New Task</@sj.a>
	
	<@sj.dialog id="addNewTaskForm" autoOpen="false" modal="true" title="Add Task" draggable="false">
    	<@s.form id="createTaskForm" action="view" cssClass="form-group">
			<@s.hidden name="postBack" value="true" />
			<@s.hidden name="dataAction" value="add" />
			<@s.textfield name="enteredTitle" label="Task Title" labelposition="top" cssClass="form-control form-group clear" />
			<@s.textarea name="enteredDescription" label="Task Description" labelposition="top" cssClass="form-control form-group clear" />
			<@sj.datepicker name="enteredDueDate" label="Due Date" labelposition="top" changeYear="true" cssClass="clear datepicker form-control form-group" />
			<@sj.submit onSuccessTopics="closedialog" value="Add" id="addTaskFormSubmit" formIds="createTaskForm" targets="result" button="true" cssClass="btn btn-default" />
		</@s.form>
	</@sj.dialog>
		
	<div id=result>
	<#include "view-ajax.ftl">
	</div>
	
</body>

</html>