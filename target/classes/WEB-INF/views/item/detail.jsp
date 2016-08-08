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
<body>
	<form:form method="post" action="/item/create" modelAttribute="itemForm">
		<div>
			Title :
			<form:input path="title" />
			Type <select name="type" id="postType"><option value="url">#URL</option>
				<option value="post">#POST</option></select>
		</div>
		<div id="url_content">
			URL
			<form:input path="url" />
		</div>
		<div id="remark_content">
			REMARKS
			<form:textarea path="remark" rows="7" cols="50" />
		</div>
		<div>
			TAGS*
			<form:input path="tags" />

		</div>
		<div>
			賣價
			<form:input path="buyoutPrice" />
		</div>
		<div>
			bid價
			<form:input path="bidPrice" />
		</div>
		<button type="submit">Create</button>

	</form:form>

</body>
</head>
</html>