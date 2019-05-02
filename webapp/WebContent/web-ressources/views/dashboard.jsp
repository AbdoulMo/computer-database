<%@include file="taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link
	href="<%=request.getContextPath()%>/web-ressources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%>/web-ressources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/web-ressources/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="msg.title" /> 
			</a><a href="?lang=en">English</a> | <a href="?lang=fr">French</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${computersFound}" />
				<spring:message code="msg.computerFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">

					<form:form id="searchForm" method="GET" action="#"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder=<spring:message code="msg.search"/> />
						<input type="submit" id="searchsubmit"
							value=<spring:message code="msg.btnSearch"/>
							class="btn btn-primary" />
					</form:form>

				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="msg.addComputer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="msg.btnEdit" /></a>
				</div>
			</div>
		</div>

		<form:form id="deleteForm" action="deleteComputer" method="POST">
			<c:forEach var="computer" items="${displayedComputer}">
				<input type="hidden" name="selection" value="${computer.getId()}">
			</c:forEach>
		</form:form>


		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="msg.computerName" /> <a
							href="dashboard?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>orderBy=name"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<th><spring:message code="msg.computerDateI" /> <a
							href="dashboard?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>orderBy=introduced"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="msg.computerDateD" /> <a
							href="dashboard?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>orderBy=discontinued"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Company -->
						<th><spring:message code="msg.computerCompany" /> <a
							href="dashboard?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>orderBy=company"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${displayedComputer}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.getId()}"></td>
							<td><a
								href="editComputer?computerIDToEdit=${computer.getId()}"
								onclick=""> <c:out value="${computer.getName()}" /></a></td>
							<td><c:out value="${computer.getIntroduced()}" /></td>
							<td><c:out value="${computer.getDiscontinued()}" /></td>
							<td><c:out value="${computer.getManufacturer_name()}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${currentPage > 1}">
					<li><a
						href="dashboard?<%=request.getParameter("search") == null ? "" : "search="+request.getParameter("search")+"&"%><%=request.getParameter("orderBy") == null ? "" : "orderBy="+request.getParameter("orderBy")+"&"%>page=${currentPage-1}">${currentPage-1}</a></li>
				</c:if>
				<li><a
					href="dashboard?<%=request.getParameter("search") == null ? "" : "search="+request.getParameter("search")+"&"%><%=request.getParameter("orderBy") == null ? "" : "orderBy="+request.getParameter("orderBy")+"&"%>page=${currentPage}">${currentPage}</a></li>
				<c:if test="${currentPage < numberOfPage}">
					<li><a
						href="dashboard?<%=request.getParameter("search") == null ? "" : "search="+request.getParameter("search")+"&"%><%=request.getParameter("orderBy") == null ? "" : "orderBy="+request.getParameter("orderBy")+"&"%>page=${currentPage+1}">${currentPage+1}</a></li>
				</c:if>
			</ul>

		</div>
	</footer>
	<script
		src="<%=request.getContextPath()%>/web-ressources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/web-ressources/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/web-ressources/js/dashboard.js"></script>

</body>
</html>