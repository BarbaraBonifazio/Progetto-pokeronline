<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Registrazione</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

	<script type="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  			
		  		$("#errorName").hide(); 
			  	$("#errorSurname").hide(); 
			  	$("#errorUsername").hide();
			  	$("#errorPassword").hide();
			  	
			  	var validate = true;
			  	
			  	if(!$("#nomeUtente")[0].value) { 
				  	$("#errorName").show();
				  	valida = false;
			  	}	
				  	if(!$("#cognomeUtente")[0].value){ 
					  	$("#errorSurname").show();
					  	valida = false;
					} 	
						if(!$("#usernameUtente")[0].value){ 
						  	$("#errorUsername").show();
						  	valida = false;
						} 						  	
						  	if(!$("#passwordUtente")[0].value){ 
							  	$("#errorPassword").show();
							  	valida = false;
							} 	 	
								  	if(!valida){ 
								  		event.preventDefault(); 
								  		return;
								  	}
			 });
		})  
		
	  </script>
</head>
<body>

	<main role="main" class="container" style="max-width: 460px;">

			<div class="alert alert-danger alert-dismissible fade show d-none"
				role="alert">
				Operazione fallita!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
	
			<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
				<c:forEach var = "errorItem" items="${userErrors }">
		        	<ul>
						<li> ${errorItem }</li>	
					</ul>
		      	</c:forEach>
			</div>
	
		<div class='card' style="width: 25rem; padding-left">
				<div class='card-header text-center'>
					<h5>Compila il modulo per registrarti</h5>
				</div>
			
			<div class='card-body'>
					<h6 class="card-title text-center">
						I campi con <span class="text-danger">*</span> sono obbligatori
					</h6>
				<form method="post"
					action="${pageContext.request.contextPath}/ExecuteInsertUserByRegistrationServlet"
					 id="form" class="needs-validation" novalidate>
							<label>Nome <span class="text-danger">*</span></label> <input
								type="text" name="nome" id="nomeUtente" class="form-control"
								placeholder="Inserire nome" value="${userAttribute.nome}" required>
								<div class="invalid-feedback" id="errorName"> Il campo nome risulta vuoto!</div>					
							<br>
							<input class="form-control" type="hidden" name="exp" id="expAccumulata" 
								 value="${userAttribute.expAccumulata }">
							<input class="form-control" type="hidden" name="credito" id="creditoAccumulato" 
								 value="${userAttribute.creditoAccumulato }">					
							<label>Cognome <span class="text-danger">*</span></label> <input
								type="text" name="cognome" id="cognomeUtente"
								class="form-control" placeholder="Inserire cognome"
								value="${userAttribute.cognome}" required>
							<div class="invalid-feedback" id="errorSurname"> Il campo cognome risulta vuoto!</div>	
							<br>		
							<label>Username <span class="text-danger">*</span></label> <input
								type="text" class="form-control" name="username"
								id="usernameUtente" placeholder="Inserire username"
								value="${userAttribute.username}" required>
							<div class="invalid-feedback" id="errorUsername"> Il campo username risulta vuoto!</div>	
							<br>
							<label>Password <span class="text-danger">*</span></label> <input
								type="password" class="form-control" name="password"
								id="passwordUtente" placeholder="Inserire password"
								value="${userAttribute.password}" required>
							<div class="invalid-feedback" id="errorPassword"> Il campo password risulta vuoto!</div>
							<br>
						<div class="text-center">	
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Registrami</button>
						</div>
				</form>
				<!-- end card-body -->
			</div>
			<!-- end class card -->
		</div>
		<!-- end container -->
	</main>
	<div class="text-center">	
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>