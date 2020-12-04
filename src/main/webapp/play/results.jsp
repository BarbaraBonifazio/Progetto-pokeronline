<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Partite</title>

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
		</div>
		
		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
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
				<h5>Lista delle Partite In corso Ricercate</h5>
			</div>
			<div class='card-body'>
			
				<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/play/PrepareSearchPartiteServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Indietro
				</a>
				<hr>
				<br>
				<c:if test="${listaTavoli.size() == 0}">
				<h6 Style="color:red; text-align:center">Non ci sono tavoli disponibili per giocare con la tua esperienza e il tuo credito attuali!</h6>
				<br>
				</c:if>
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Esperienza Minima</th>
								<th>Puntata Minima</th>
								<th>Denominazione</th>
								<th>Data Creazione</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.listaTavoli}"
								var="tavolo">
								<tr>
	
									<td>${tavolo.expMin}</td>
									<td>${tavolo.cifraMin}</td>
									<td>${tavolo.denominazione}</td>
									<td>${tavolo.dataCreazione}</td>

											
											<!-- BOTTONE GIOCA -->
									<td><a class="btn  btn-sm btn-outline-secondary" 
										href="${pageContext.request.contextPath}
												/play/PreparePartitaServlet?idParamTavoloPerGioca=${tavolo.id }
												">Gioca</a>
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