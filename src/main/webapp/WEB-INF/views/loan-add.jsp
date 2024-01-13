<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
	<title>Loan</title>
</head>

<body>
    <h2>Loam Management System</h2>
		<h3> Add Loan</h3>
		<form:form action="saveLoan" modelAttribute="loan" method="POST">
		<form:hidden path="id"/>
		<table>
			<tbody>
				<tr>
                	<th> First Name</th>
                	<td><form:input path="firstName"/><form:errors path="firstName"/></td>
                	</tr>
				<tr>
					<th> Last Name</th>
					<td><form:input path="lastName"/> <form:errors path="lastName"/></td>
				</tr>
				<tr>
					<th> Email</th>
					<td><form:input path="email" /> <form:errors path="email"/></td>
				</tr>
				<tr>
					<th> Amount </th>
					<td><form:input path="amount" type = "number" step="0.01" /> <form:errors path="amount" /></td>
				</tr>
				<tr>
					<th> Interest </th>
					<td><form:input path="interest" type = "number" step="1" /><form:errors path="interest" /></td>
				</tr>
				<tr>
					<th> Term </th>
					<td><form:input path="term"/> <form:errors path="term"/></td>
				</tr>

				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			</tbody>
		</table>
		</form:form>
	</div>
	<br><br>
	<br><br>
	<div id="container">
			<a href="${pageContext.request.contextPath}/loan/list">Back to List Page</a>
	</div>
</body>
</html>