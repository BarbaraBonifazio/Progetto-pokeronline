<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Cerca Tavoli</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

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
				<h5>Cerca Tavoli</h5>
			</div>
			<div class='card-body'>
				
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/tavolo/gestioneTavolo.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				<hr>
				<form method="get"
					action="${pageContext.request.contextPath}/tavolo/ExecuteFindTavoliServlet"
					novalidate>
					
					<input class="form-control" type="hidden" name="idUtente" id="idUtenteCreatore" 
								 value="${utentePerSearchTavoli.id }">
					
					<div class="form-group col-md-6">
						<label>Denominazione</label> <input type="text" name="denominazione"
							id="denominazione" class="form-control"
							placeholder="Inserire almeno i primi tre caratteri" value="${tavoloAttribute.denominazione }" required>
					</div>
					
					<div class="form-group col-md-6">
						<label>Data Creazione</label> <input type="date" name="data"
							id="dataCreazione" class="form-control" value="${tavoloAttribute.dataCreazione }"required>
					</div>

					<div class="form-group col-md-6">
						<label>Puntata Minima</label> 
						<input type="number" name="cifraMin"
							id="cifraMinima" class="form-control"
							placeholder="Inserire Puntata Minima" value="${tavoloAttribute.cifraMin }"required
							min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary" Style="background-color:green; border-color:#327827">Cerca</button>

				</form>
			
				<!-- end card-body -->
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />
</body>
</html>
