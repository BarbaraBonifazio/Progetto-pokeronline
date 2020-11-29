<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Tavoli</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
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
		
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Lista dei tuoi Tavoli Creati</h5>
			</div>
			<div class='card-body'>
			
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/PrepareSearchTavoliServlet?idUser=${user.id}"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>

				<%-- </c:if> --%>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Denominazione</th>
								<th>Esperienza Minima</th>
								<th>Data Creazione</th>
								<th>Puntata Minima</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaTavoli}"
								var="tavolo">
								<tr>

									<td>${tavolo.denominazione}</td>
									<td>${tavolo.expMin}</td>
									<td>${tavolo.cifraMin}</td>
									<td>${tavolo.dataCreazione}</td>

											
											<!-- BOTTONE VISUALIZZA -->
									<td><a Style="color:green !important" class="btn  btn-sm btn-outline-secondary" 
										href="${pageContext.request.contextPath}
												/FindTavoloServlet?idParamPerDettaglioTavolo=${tavolo.id}
												">Visualizza</a>
												
											<!-- BOTTONE MODIFICA -->
										<a Style="color:green !important" class="btn  btn-sm btn-outline-primary"
										href="${pageContext.request.contextPath}
														/PrepareUpdateTavoloServlet?idDaInviareAExecuteUpdate=${tavolo.id}
														">Modifica</a>
														
											<!-- BOTTONE ELIMINA -->
										<a Style="color:green !important" class="btn btn-outline-danger btn-sm"
										href="${pageContext.request.contextPath}
														/ConfirmDeleteTavoloServlet?idDaInviareAExecuteDelete=${tavolo.id}&
												denominazionePerTornareAllaRicercaEffettuata=${requestScope.titoloPerTornareAllaRicercaEffettuata}&
												expMinaPerTornareAllaRicercaEffettuata=${requestScope.tramaPerTornareAllaRicercaEffettuata}&
												cifraMinPerTornareAllaRicercaEffettuata=${requestScope.generePerTornareAllaRicercaEffettuata}&
												dataCreazionePerTornareAllaRicercaEffettuata=${requestScope.autorePerTornareAllaRicercaEffettuata}
														">Cancella</a>
										
									</td>
								</tr> <!-- END tabella  -->
							</c:forEach> <!-- END forEach lista Libri per passare i parametri anche tramite href -->
						</tbody>
					</table>
				</div>

				<!-- end card-body -->
			</div>
		</div>



		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>