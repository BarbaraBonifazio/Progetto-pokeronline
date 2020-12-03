<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>

<title>Gioca</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
	<jsp:include page="../header.jsp" />

</head>

<body>
	<div class="container">

		<div class='card'>
			<div class="text-center">
				<div class='card-header'>
					<h4>You are playing now!</h4>
				</div>
	
				<div
					class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
					role="alert">
					${errorMessage}
				</div>
		
				<div
					class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
					role="alert">
					${successMessage}
						<span aria-hidden="true">&times;</span>
				</div>
	
				<div class='card-body'>
	
					<form method="get"
						action="${pageContext.request.contextPath}/play/ExecutePartitaServlet"
						novalidate="novalidate">
						
						<input class="form-control" type="hidden" name="idUser" 
									id="idUserCreatore" value="${user.id }">
							<br><br><br><br><br>
							<div class="form-group col-md-12">
									<button type="submit" class="btn btn-primary btn-lg" Style="background-color:green !important;
									border-color:#327827">Gioca</button>
							</div>
						
							<br><br><br><br><br>
							<div class="form-group col-md-12">
									<a class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/play/PreparePlayManagementServlet">Lascia</a>
							</div>
							
									
	
						
					</form>
					<!-- end card-body -->
				</div>

			</div>
		
		</div>
		<!-- /.container -->
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>