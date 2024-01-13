<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
	<title>List Loan</title>
</head>

<body>
	<h2> Loan Management System</h2>
		<input type="button" value="Add Loan"
			   onclick="window.location.href='showFormForAdd'; return false;"/>

    <h2> Search Loan </h2>
      <form:form action="/search?page=0&size=5" method="POST">
            <input type="text" placeholder="Search By Loan First/Name" name="theSearchName" value = "${theSearchName}">
            <input type="submit" value="Search"/>
      </form:form>

		<table border = "1">
			<tr>
			    <th> Loan No.
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=id,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=id"> Asc </a>
			    </th>
                <th> First Name
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=firstName,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=firstName"> Asc </a>
                </th>
                <th> Last Name
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=lastName,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=lastName"> Asc </a>
                </th>
                <th> Email
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=email,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=email"> Asc </a>
                </th>
                <th> Amount
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=amount,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=amount"> Asc </a>
                </th>
                <th> Interest
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=interest,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=interest"> Asc </a>
                </th>
                <th> Term
				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=term,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&sort=term"> Asc </a>
                </th>
				<th> Action</th>
			</tr>
			<c:forEach var="tempLoan" items="${loans}">

			<c:url var="updateLink" value="/loan/showFormForUpdate">
				<c:param name="loanId" value="${tempLoan.id}"/>
			</c:url>
			<c:url var="deleteLink" value="/loan/showFormForDelete">
				<c:param name="loanId" value="${tempLoan.id}"/>
			</c:url>

					<tr>
						<td> Loan No. - ${tempLoan.id}</td>
						<td> ${tempLoan.firstName}</td>
						<td> ${tempLoan.lastName}</td>
						<td> ${tempLoan.email}</td>
						<td> ${tempLoan.amount}</td>
						<td> ${tempLoan.interest}</td>
						<td> ${tempLoan.term}</td>
						<td>
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this loan?'))) return false">
							|Clear</a>
							<c:if test = "${tempLoan.active eq true}">
                                     <a href="/updateStatus?active=false&id=${tempLoan.id}">Close Loan</a>
                            </c:if>
                            <c:if test = "${tempLoan.active eq false}">
                                     Loan Completed
                            </c:if>
						</td>
					</tr>
			</c:forEach>
		</table>
	<br><br>
        	<c:choose>
                <c:when test="${totalPage == 0}">
                    No Record Found
                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                            &nbsp &nbsp<a href="/list?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
</body>
</html>