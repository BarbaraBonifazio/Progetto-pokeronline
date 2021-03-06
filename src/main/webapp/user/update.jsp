<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:if test="${sessionScope.utente eq null || sessionScope.utente.ruolo == 'guest'}"><c:redirect url="LogoutServlet"/></c:if> --%>
<%@page import="it.pokeronline.model.user.User"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica User</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

	<script type="text/javascript">
	
		 $(document).ready(function() { 
		  	$("form").submit(function( event ) {
		  			
		  		$("#errorNome").hide(); 
			  	$("#errorCognome").hide(); 
			  	$("#errorUsername").hide();
			  	$("#errorStato").hide();
			  	$("#errorRuoli").hide(); 
			  	
			  	var validate = true;
			  	
			  	if(!$("#nomeUser")[0].value) { 
				  	$("#errorNome").show();
				  	valida = false;
			  	}	
				  	if(!$("#cognomeUser")[0].value){ 
					  	$("#errorCognome").show();
					  	valida = false;
					} 	
						if(!$("#usernameUser")[0].value){ 
						  	$("#errorUsername").show();
						  	valida = false;
						} 						  	
						  	if(!$("#statoUser")[0].value){ 
							  	$("#errorStato").show();
							  	valida = false;
							} 	
							  	if(!$("input[type='checkbox']").is(":checked")){ 
								  	$("#errorRuoli").show();
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
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

	<c:forEach items="${requestScope.errorMessage}" var="errore">
		<div
			class="alert alert-danger alert-dismissible fade show ${errore==null?'d-none': ''}"
			role="alert">
			${errore}
		</div>
	</c:forEach>
	
		<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${userErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
		</div>
	
<div class='card'>

			<div class='card-header'>
				<h5>Modifica User</h5>
			</div>
		
	<div class='card-body'>
	
		<a Style="color:green !important" class="text-right"
			href="${pageContext.request.contextPath}/user/ExecuteListAllUsersServlet"
			class='btn btn-outline-secondary' style='width: 80px'> <i
			class='fa fa-chevron-left'></i> Indietro
		</a>
		
		<hr>
	
		<h6 class="card-title">
			I campi con <span class="text-danger">*</span> sono obbligatori
		</h6>

		<form method="post"
			action="${pageContext.request.contextPath}/user/ExecuteUpdateUserServlet"
			id="form" class="needs-validation" novalidate>

			<div class="form-row">
				<Input type="hidden" name="idUserPerUpdate" id="idUser"
					class="form-control" value="${idUserPerUpdate}" required>

				<div class="form-group col-md-6">
					<label>Nome <span class="text-danger">*</span></label> 
						<input type="text" name="nome" id="nomeUser" class="form-control"
							placeholder="Inserire nome" value="${userAttribute.nome}" required>
						<div class="invalid-feedback" id="errorNome"> Il campo nome risulta vuoto!</div>
				</div>

				<div class="form-group col-md-6">
					<label>Cognome <span class="text-danger">*</span></label> 
						<input type="text" name="cognome" id="cognomeUser" class="form-control"
							placeholder="Inserire cognome" value="${userAttribute.cognome}" required>
						<div class="invalid-feedback" id="errorCognome"> Il campo cognome risulta vuoto!</div>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Username <span class="text-danger">*</span></label> 
						<input type="text" class="form-control" name="username"
							id="usernameUser" placeholder="Inserire username"
							value="${userAttribute.username}" required>
						<div class="invalid-feedback" id="errorUsername"> Il campo username risulta vuoto!</div>
				</div>
	
				<c:if test="${isCreato}">
				<div class="form-group col-md-6">
					<label>Stato</label> 
					<select id="statoUser" name="stato" class="form-control" required>
						<option value="${userAttribute.stato}" selected="selected">-Selezionare Stato-</option>
						<c:forEach items="${listaStati}" var="stato">
							<c:if test="${stato != 'DISABILITATO'}"> 
								<option value="${stato}" ${stato == userAttribute.stato ? 'selected="selected"' : ''}>${stato}</option>
							</c:if>
						</c:forEach>
					</select>
					<div class="invalid-feedback" id="errorStato"> Non risulta selezionato alcuno stato!</div>
				</div>
				
				<div class="form-group col md-6">
					<label for="exampleFormControlSelect1">Ruoli</label>
					<c:forEach items="${listaRuoli}" var="ruolo">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" required
								value="${ruolo.id}" id="ruoli" name="idRuoli"
								<c:forEach items="${userAttribute.ruoli}" var="ruoloSelezionato">
									${ruoloSelezionato.id eq ruolo.id ? 'checked' : ''} 
								</c:forEach>
							>
							<label class="form-check-label" for="defaultCheck1">
								${ruolo.codice} </label>	
						</div>	
					</c:forEach>
					<div class="invalid-feedback" id="errorRuoli"> Non risulta selezionato alcun ruolo!</div>
				</div>
				</c:if> 
					
					<c:if test="${!isCreato}">
					<Input type="hidden" name="stato"
						id="statoNascosto" class="form-control"
						value="${userAttribute.stato}">
						
						<c:forEach items="${userAttribute.ruoli}" var="ruoloSelezionato">
							<input class="form-check-input" type="hidden" required
								value="${ruoloSelezionato.id}" id="ruoli" name="idRuoli">
						</c:forEach>
					</c:if>
			</div>
			
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>
		</form>

	<!-- end card-body -->
	</div>
</div>

		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>