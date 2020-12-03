<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>

<title>Disattiva User</title>

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
					<h4>Confermi di voler disattivare questo Utente?</h4>
					<c:if test="${isInGioco == true}">
					<h6 Style="color:red">Attenzione! L'utente verrà rimosso dalla partita in corso!</h6>
					</c:if>
				</div>
	
	
				<div class='card-body'>
	
					<form method="get"
						action="${pageContext.request.contextPath}/user/ExecuteDisattivaUserServlet"
						novalidate="novalidate">
						
						<div class="form-row">
							<div class="form-group col-md-6">
								
									<a class="btn btn-danger btn-lg" role="button"
									href="${pageContext.request.contextPath}/user/ExecuteListAllUsersServlet?
									">Annulla</a>
										
									<input class="form-control" type="hidden" 
									id="idUserNascosto" name="id" 
									value="${userAttribute.id }">	
										
							</div>
							<div class="form-group col-md-6">
									<button type="submit" class="btn btn-primary btn-lg" Style="background-color:green !important;
									border-color:#327827">Conferma</button>
							</div>
	
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