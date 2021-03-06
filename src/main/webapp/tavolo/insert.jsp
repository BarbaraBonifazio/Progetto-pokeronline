<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci Tavolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

<script type ="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  		
		  		var valida = true;
		  		
			  	$("#errorExpMin").hide(); 
			  	$("#errorCifraMin").hide(); 
			  	$("#errorDenominazione").hide();
			  	
				  	if(!$("#esperienzaMinima")[0].value) { 
					  	$("#errorExpMin").show();
					  	valida = false;
				  	}	
						  	if(!$("#cifraMinima")[0].value){ 
							  	$("#errorCifraMin").show();
							  	valida = false;
							} 	
						  	
							  	if(!$("#denominazione")[0].value){ 
								  	$("#errorDenominazione").show();
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
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Inserisci Tavolo</h5>
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/tavolo/gestioneTavolo.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
			</div>
			<div class='card-body'>
				
				<form method="post"
					action="${pageContext.request.contextPath}/tavolo/ExecuteInsertTavoloServlet"
					novalidate>
					
					<input class="form-control" type="hidden" name="idUser" id="idUserCreatore" 
								 value="${idUserPerInsertTavolo }">

					<div class="form-group col-md-6">
						<label>Esperienza Minima</label> <input type="number" name="expMin"
							id="esperienzaMinima" class="form-control"
							placeholder="Inserire Esperienza Minima" value="${tavoloAttribute.expMin }" required
							min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
							<div class="invalid-feedback" id="errorExpMin"> Il campo Esperienza Minima risulta vuoto!</div>
					</div>

					<div class="form-group col-md-6">
						<label>Puntata Minima</label> 
						<input type="number" name="cifraMin"
							id="cifraMinima" class="form-control"
							placeholder="Inserire Puntata Minima" value="${tavoloAttribute.cifraMin }" required
							min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
							<div class="invalid-feedback" id="errorCifraMin"> Il campo Puntata Minima risulta vuoto!</div>
					</div>
					
					<div class="form-group col-md-6">
						<label>Denominazione</label> <input type="text" name="denominazione"
							id="denominazione" class="form-control"
							placeholder="Inserire denominazione" value="${tavoloAttribute.denominazione }" required>
							<div class="invalid-feedback" id="errorDenominazione"> Il campo Denominazione risulta vuoto!</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary" Style="background-color:green; border-color:#327827">Crea</button>

				</form>

				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
