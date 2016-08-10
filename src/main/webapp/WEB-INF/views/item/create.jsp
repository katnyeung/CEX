<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
<style type="text/css">
div {
	margin: 20px;
}
</style>
<body>
	<form:form method="post" action="/item/create" modelAttribute="itemForm">
		<table cellpadding="10" cellspacing="10">
			<tr>
				<td>Title :</td>
				<td><form:input path="title" style="width:300px" /></td>
			</tr>
			<tr>
				<td>URL :</td>
				<td><form:input path="url" style="width:400px" /></td>
			</tr>
			<tr>
				<td>REMARKS :</td>
				<td><form:textarea path="remark" rows="7" cols="50" /></td>
			</tr>
			<tr>
				<td>TAGS* :</td>
				<td><form:input path="tags" style="width:400px" /></td>
			</tr>
			<tr>
				<td>賣價 (for reference) :</td>
				<td><form:input path="buyoutPrice" /></td>
			</tr>
		</table>


		<button type="submit">Create</button>

	</form:form>

</body>
</head>
</html>