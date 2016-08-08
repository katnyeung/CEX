<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form:form method="post" action="/login"
		modelAttribute="loginForm">
		<div>
			Email
			<form:input path="email" />
		</div>
		<div id="url_content">
			Password
			<form:password path="password" />
		</div>

		<button type="submit">Login</button>

	</form:form>

</body>
</head>
</html>