<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" />
<!-- <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script> -->
<!-- <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->

<@sj.head/>

<script type="text/javascript">
$.subscribe('ajaxcompletedialog',function(event,data){
    $("#addNewTaskForm").dialog('close');
});

$.subscribe('opendialog',function(event,data){
    $("#addNewTaskForm").dialog('open');
});
</script>

<style>
#addNewForm label {
	color: black;
}
.ui-datepicker-trigger {
	display: none;
}
#addTaskForm label {
	color: black;
}
</style>

<title>View Tasks</title>

</head>
<body>
	<@sj.a onClickTopics="opendialog" class="btn">Add New Task</@sj.a>
	
	<div id=result>
	<#include "view-ajax.ftl">
	</div>
	
</body>

</html>