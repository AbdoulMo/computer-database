<%@include file="taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<a class="navbar-brand" href="dashboard"> <spring:message code="msg.title"/> </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computerToEdit.id}</div>
					<h1><spring:message code="msg.editComputer"/></h1>
					<c:if test="${computerToEdit != null}">
						<form id="editComputerForm" action="editComputer"
							method="POST">
							<input type="hidden" name="computerID"
								value="${computerToEdit.getId()}" id="id" />
							<fieldset>
								<div class="form-group">
									<label for="computerName"><spring:message code="msg.computerName"/></label>
									<input type="text"
										class="form-control" id="computerName" name="computerName"
										placeholder="Computer name"
										value="${computerToEdit.getName()}"
										data-validation="alphanumeric"
										data-validation-ignore=" ,-,+,.,&,/,:"
										data-validation-error-msg="Nom d'entreprise invalide 
										seul les caractères alphanumérique ainsi 
										que les caractères suivants sont autorisé: espace, -, +, ., &, /, :" />
								</div>
								<div class="form-group">
									<label for="introduced"><spring:message code="msg.computerDateI"/></label> 
									<input
										type="date" class="form-control" id="introduced"
										name="introduced" placeholder="Introduced date"
										value="${computerToEdit.getIntroduced()}"
										data-validation="date" />
								</div>
								<div class="form-group">
									<label for="discontinued"><spring:message code="msg.computerDateD"/></label> 
									<input
										type="date" class="form-control" id="discontinued"
										name="discontinued" placeholder="Discontinued date"
										value="${computerToEdit.getDiscontinued()}"
										data-validation="date"/>
								</div>
								<div class="form-group">
									<label for="companyId"><spring:message code="msg.computerCompany"/></label> 
										<select
										class="form-control" id="companyId" name="companyId"
										data-validation="number"
										data-validation-error-msg="Choix d'entreprise invalide">
										<option value="invalid"><spring:message code="msg.selectCompany"/></option>
										<c:forEach var="company" items="${companyList}">
											<option value="${company.getId()}"
												${computerToEdit.getManufacturer_name() == company.getName() ? 'selected' : ''}>${company.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</fieldset>
							<div class="actions pull-right">
								<input type="submit" value=<spring:message code="msg.btnEdit"/> class="btn btn-primary">
								or <a href="dashboard" class="btn btn-default"><spring:message code="msg.btnCancel"/></a>
							</div>
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.79/jquery.form-validator.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/web-ressources/js/formValidator.js"></script>
</body>
</html>