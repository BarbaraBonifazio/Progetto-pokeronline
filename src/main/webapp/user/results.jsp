<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.user.User"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista Utenti</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

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
				<h5>Lista degli utenti ricercati</h5>
			</div>
			
			
			<div class='card-body'>	
				
					<a Style="color:green !important" class="text-right"
					href="${pageContext.request.contextPath}/user/PrepareFindUsersServlet"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Nuova Ricerca
					</a>
				
				<div class='table-responsive'>
					<table class='table table-striped '>
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Data Registrazione</th>
								<th>Stato</th>
								<th>Ruolo</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${requestScope.usersPerResults}"
								var="userResult">
								<tr>
									
									<td>${userResult.nome}</td>
									<td>${userResult.cognome}</td>
									<td>${userResult.username}</td>
									<td>${userResult.dataRegistrazione}</td>
									<td>${userResult.stato}</td>
									<td>${userResult.ruoli}</td>

												<!-- BOTTONE VISUALIZZA -->
									<td><a class="btn  btn-sm btn-outline-secondary"
										href="${pageContext.request.contextPath}
												/user/FindUserServlet?idParamPerDettaglioUser=${userResult.id}
												">Visualizza</a>
										
											<!-- BOTTONE MODIFICA -->
										<a Style="color:green !important; border-color:#327827" class="btn  btn-sm btn-outline-primary"
											href="${pageContext.request.contextPath}
												/user/PrepareUpdateUserServlet?idDaInviareAExecuteUpdate=${userResult.id}
												">Modifica</a>
										<br>	
											<!-- BOTTONE ELIMINA -->
										<a class="btn btn-outline-danger btn-sm"
											href="${pageContext.request.contextPath}
												/user/ConfirmDeleteUtenteServlet?idDaInviareAExecuteDelete=${userResult.id}
												">Attiva/Disattiva</a>
									</td>
	
							</tr> <!-- END TABELLA -->
							</c:forEach> <!-- END forEach listaUtenti per passare parametri tramite href -->	
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