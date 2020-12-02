<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.pokeronline.model.tavolo.Tavolo"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza User</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Visualizza Dettaglio User</div>

			
			<div class='card-body'>
				<dl class="row">


					<dt class="col-sm-3 text-right">Id User:</dt>
					<dd class="col-sm-9">${userPerShow.id}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">${userPerShow.nome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">${userPerShow.cognome}</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Username:</dt>
					<dd class="col-sm-9">${userPerShow.username}</dd>
				</dl>	
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Data Registrazione:</dt>
					<dd class="col-sm-9">${userPerShow.dataRegistrazione}</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Stato:</dt>
					<dd class="col-sm-9">${userPerShow.stato}</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Ruoli:</dt>
					<dd class="col-sm-9">${userPerShow.ruoli}</dd>
				</dl>
 			</div>
		

			<div class='card-footer'>
				<a Style="color:green !important" href="${pageContext.request.contextPath}/user/PrepareFindUsersServlet"
					class='btn btn-outline-secondary' style='width: 90px'> <i
					class='fa fa-chevron-left'></i> Nuova Ricerca
				</a>
			</div>
		</div> 
		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>