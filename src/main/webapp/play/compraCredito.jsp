<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Acquista</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

<script type ="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  		
		  		var valida = true;
		  		
			  	$("#errorCredito").hide(); 
			  	
				  	if(!$("#creditoAcquistato")[0].value) { 
					  	$("#errorCredito").show();
					  	valida = false;
				  	}	
								  	if(!valida){ 
								  		event.preventDefault(); 
								  	}
			 });
		})  
		
	  </script>

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
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

			<div class='card-body'>
				
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/play/PreparePlayManagementServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				
				<hr>
				
				<form method="post"
					action="${pageContext.request.contextPath}/play/ExecuteCompraCreditoServlet"
					novalidate>
					
					<input class="form-control" type="hidden" name="idUser" id="idUserCreatore" 
								 value="${user.id }">

					<div class="form-group col-md-6">
						<label>Cifra</label> <input type="number" name="credito"
							id="creditoAcquistato" class="form-control"
							placeholder="Inserire importo" value="${cifraAttribute }" required>
							<div class="invalid-feedback" id="errorCredito"> Il campo Cifra non è stato valorizzato correttamente!</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>

				</form>

				<!-- end card-body -->
			</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
