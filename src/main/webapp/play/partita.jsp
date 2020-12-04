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

	<jsp:include page="../navbar.jsp"></jsp:include>
	
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
				</div>
	
				<div class='card-body' Style="background: linear-gradient(to bottom, #ccffcc 5%, #66d856 100%);">
	
					<form method="post"
						action="${pageContext.request.contextPath}/play/ExecutePartitaServlet"
						novalidate="novalidate">
						
						<input class="form-control" type="hidden" name="idUser" 
									id="idUserCreatore" value="${user.id }">
							<br><br>
								
							<div class="form-group col-md-12">
									<button type="submit" class="btn btn-primary btn-lg" Style="background-color:green !important;
									border-color:#327827">Gioca</button>
							</div>
							
						
							<br><br>
							<div class="form-group col-md-12">
									<a class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/play/ExecuteLasciaPartitaServlet">Lascia</a>
							</div>
							
							<br><br><br><br><br>
						<c:if test="${user.hasCreditoRichiesto() eq 'false' }">	
						<h4 Style="color:red"> Il tuo credito è insufficiente per giocare a questo tavolo! </h4>
						<br>
						<div class="form-group col-md-12">
									<a Style="color:white !important; border-color:#327827; background-color:#327827" class="btn  btn-md btn-outline-primary"
								href="${pageContext.request.contextPath}/play/PreparePlayManagementServlet">Torna alla Home</a>
						</div>
						
						<div class="form-group col-md-12">
									<a Style="color:white !important; border-color:#327827; background-color:#327827" class="btn  btn-md btn-outline-primary"
								href="${pageContext.request.contextPath}/play/PrepareCompraCreditoServlet">Compra Credito</a>
						</div>
						</c:if>
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